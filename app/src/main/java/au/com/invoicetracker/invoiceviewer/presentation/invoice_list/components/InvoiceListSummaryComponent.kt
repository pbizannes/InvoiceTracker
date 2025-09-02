package au.com.invoicetracker.invoiceviewer.presentation.invoice_list.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import au.com.invoicetracker.invoiceviewer.presentation.InvoiceSummary
import au.com.invoicetracker.invoiceviewer.presentation.InvoiceViewModel
import au.com.invoicetracker.invoiceviewer.util.formatCents

@Composable
fun InvoiceListSummaryComponent(
    viewModel: InvoiceViewModel = hiltViewModel(),
) {
    InvoiceSummary(viewModel.getInvoicesTotal().formatCents())
}
