package au.com.invoicetracker.invoiceviewer.data.source

import au.com.invoicetracker.exceptions.InvoiceRetrievalException
import au.com.invoicetracker.invoiceviewer.data.source.network.InvoiceService
import au.com.invoicetracker.invoiceviewer.domain.models.Invoice
import au.com.invoicetracker.invoiceviewer.domain.models.InvoiceItem
import au.com.invoicetracker.invoiceviewer.domain.models.InvoiceResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class DefaultInvoiceRepositoryTest {

    private lateinit var invoiceService: InvoiceService
    private lateinit var repository: DefaultInvoiceRepository

    private val sampleItems = listOf(InvoiceItem("item1", "Test Item", 1, 100))
    private val sampleInvoices = listOf(
        Invoice("1", "2023-01-01", "Desc 1", sampleItems),
        Invoice("2", "2023-01-02", "Desc 2", sampleItems)
    )

    @Before
    fun setUp() {
        invoiceService = mock()
        repository = DefaultInvoiceRepository(invoiceService)
    }

    @Test
    fun `getInvoices returns success when service call is successful with body`() = runTest {
        val mockResponseBody = InvoiceResponse(sampleInvoices)
        val mockResponse = Response.success(mockResponseBody)
        whenever(invoiceService.getInvoices()).thenReturn(mockResponse)

        val result = repository.getInvoices()

        assertTrue("Should be a success", result.isSuccess)
        assertEquals(sampleInvoices, result.getOrNull())
    }

    @Test
    fun `getInvoices returns failure when service call is successful but body is null`() = runTest {
        val mockResponse = Response.success<InvoiceResponse>(null) // Success with null body
        whenever(invoiceService.getInvoices()).thenReturn(mockResponse)

        val result = repository.getInvoices()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }

    @Test
    fun `getInvoices returns failure when service call is not successful`() = runTest {
        val errorResponseBody = """{"error":"Not Found"}""".toResponseBody(null)
        val mockResponse = Response.error<InvoiceResponse>(404, errorResponseBody)
        whenever(invoiceService.getInvoices()).thenReturn(mockResponse)

        val result = repository.getInvoices()

        assertTrue(result.isFailure)
        val exception = result.exceptionOrNull()
        assertTrue(exception is InvoiceRetrievalException)
        assertNotNull(exception?.message) // Default message from InvoiceRetrievalException
    }

    @Test
    fun `getInvoices returns failure when service call throws an exception`() = runTest {
        val networkException = RuntimeException("Network connection failed")
        whenever(invoiceService.getInvoices()).thenThrow(networkException)

        val result = repository.getInvoices()

        assertTrue(result.isFailure)
        val exception = result.exceptionOrNull()
        assertTrue(exception is InvoiceRetrievalException)
        assertEquals(networkException, exception?.cause) // Check that the original exception is wrapped
        assertNotNull(exception?.message)
    }
}