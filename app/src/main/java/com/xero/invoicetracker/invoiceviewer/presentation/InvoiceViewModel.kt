package com.xero.invoicetracker.invoiceviewer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.invoicetracker.invoiceviewer.domain.repository.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val invoiceRepository: InvoiceRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(InvoiceListState())
    val uiState: StateFlow<InvoiceListState> = _uiState.asStateFlow()

    fun loadInvoices() {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val result = invoiceRepository.getInvoices()
            val invoiceList = result.getOrNull()
            if (result.isSuccess) {
                if (invoiceList.isNullOrEmpty()) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            invoiceList = listOf(),
                            error = "No invoices found"
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            invoiceList = invoiceList,
                            error = null
                        )
                    }
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        invoiceList = listOf(),
                        error = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }

    fun getInvoicesTotal(): Int {
        return uiState.value.invoiceList.sumOf { invoice ->
            invoice.items.sumOf { item -> item.priceInCents * item.quantity }
        }
    }
}