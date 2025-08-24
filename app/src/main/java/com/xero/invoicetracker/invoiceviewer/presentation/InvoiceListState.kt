package com.xero.invoicetracker.invoiceviewer.presentation

import androidx.compose.runtime.Immutable
import com.xero.invoicetracker.invoiceviewer.domain.models.Invoice

@Immutable
data class InvoiceListState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val invoiceList: List<Invoice> = listOf(),
)
