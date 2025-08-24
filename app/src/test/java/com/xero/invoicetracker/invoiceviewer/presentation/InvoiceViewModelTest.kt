package com.xero.invoicetracker.invoiceviewer.presentation

import app.cash.turbine.test
import com.xero.invoicetracker.invoiceviewer.domain.models.Invoice
import com.xero.invoicetracker.invoiceviewer.domain.models.InvoiceItem
import com.xero.invoicetracker.invoiceviewer.domain.repository.InvoiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class InvoiceViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: InvoiceViewModel
    private lateinit var mockRepository: InvoiceRepository

    private val sampleInvoiceItems1 = listOf(
        InvoiceItem("item1", "Item A", 2, 100), // 200
        InvoiceItem("item2", "Item B", 1, 50)   // 50
    )
    private val sampleInvoiceItems2 = listOf(
        InvoiceItem("item3", "Item C", 3, 200)  // 600
    )
    private val sampleInvoices = listOf(
        Invoice("inv1", "2023-01-01", "Invoice 1", sampleInvoiceItems1), // Total 250
        Invoice("inv2", "2023-01-02", "Invoice 2", sampleInvoiceItems2)  // Total 600
    ) // Overall Total = 850

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set main dispatcher for viewModelScope
        mockRepository = mock()
        viewModel = InvoiceViewModel(mockRepository) // Initialize ViewModel here for initial state tests if needed
    }

    @Test
    fun `loadInvoices success - updates uiState with invoices`() = runTest(testDispatcher) {
        val expectedInvoices = sampleInvoices
        whenever(mockRepository.getInvoices()).thenReturn(Result.success(expectedInvoices))

        viewModel.loadInvoices()

        viewModel.uiState.test {
            var emitted = awaitItem() // Initial state or state after isLoading=true
            if (!emitted.isLoading) emitted = awaitItem() // Catch isLoading=true if emitted separately
            assertTrue("Should be loading", emitted.isLoading)

            emitted = awaitItem() // Final state after loading
            assertFalse("Should not be loading", emitted.isLoading)
            assertEquals(expectedInvoices, emitted.invoiceList)
            assertNull("Error should be null", emitted.error)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `loadInvoices success but no invoices - updates uiState with empty list and specific error`() = runTest(testDispatcher) {
        val emptyList = emptyList<Invoice>()
        whenever(mockRepository.getInvoices()).thenReturn(Result.success(emptyList))

        viewModel.loadInvoices()

        viewModel.uiState.test {
            var emitted = awaitItem()
            if (!emitted.isLoading) emitted = awaitItem()
            assertTrue(emitted.isLoading)

            emitted = awaitItem()
            assertFalse(emitted.isLoading)
            assertTrue(emitted.invoiceList.isEmpty())
            assertEquals("No invoices found", emitted.error)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `loadInvoices failure - updates uiState with error message`() = runTest(testDispatcher) {
        val errorMessage = "Network error occurred"
        whenever(mockRepository.getInvoices()).thenReturn(Result.failure(RuntimeException(errorMessage)))

        viewModel.loadInvoices()

        viewModel.uiState.test {
            var emitted = awaitItem()
            if (!emitted.isLoading) emitted = awaitItem()
            assertTrue(emitted.isLoading)

            emitted = awaitItem()
            assertFalse(emitted.isLoading)
            assertTrue(emitted.invoiceList.isEmpty())
            assertEquals(errorMessage, emitted.error)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getInvoicesTotal calculates sum correctly for multiple invoices and items`() = runTest(testDispatcher) {
        whenever(mockRepository.getInvoices()).thenReturn(Result.success(sampleInvoices))
        viewModel.loadInvoices() // Load the data into uiState

        testDispatcher.scheduler.advanceUntilIdle()

        val total = viewModel.getInvoicesTotal()

        assertEquals(2 * 100 + 1 * 50 + 3 * 200, total) // 200 + 50 + 600 = 850
    }

    @Test
    fun `getInvoicesTotal returns zero when invoice list is empty`() = runTest(testDispatcher) {
        whenever(mockRepository.getInvoices()).thenReturn(Result.success(emptyList()))
        viewModel.loadInvoices()
        testDispatcher.scheduler.advanceUntilIdle()

        val total = viewModel.getInvoicesTotal()

        assertEquals(0, total)
    }

    @Test
    fun `getInvoicesTotal returns zero when invoices have no items`() = runTest(testDispatcher) {
        val invoicesWithNoItems = listOf(
            Invoice("inv3", "2023-01-03", "No Items 1", emptyList()),
            Invoice("inv4", "2023-01-04", "No Items 2", emptyList())
        )
        whenever(mockRepository.getInvoices()).thenReturn(Result.success(invoicesWithNoItems))
        viewModel.loadInvoices()
        testDispatcher.scheduler.advanceUntilIdle()

        val total = viewModel.getInvoicesTotal()

        assertEquals(0, total)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher
    }
}