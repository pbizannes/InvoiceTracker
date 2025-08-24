package com.xero.invoicetracker.invoiceviewer.presentation.invoice_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.xero.invoicetracker.invoiceviewer.presentation.InvoiceSummary
import com.xero.invoicetracker.invoiceviewer.presentation.InvoiceViewModel
import com.xero.invoicetracker.invoiceviewer.util.formatCents

@Composable
fun InvoiceListSummaryComponent(
    viewModel: InvoiceViewModel = hiltViewModel(),
) {
    InvoiceSummary(viewModel.getInvoicesTotal().formatCents())
}
