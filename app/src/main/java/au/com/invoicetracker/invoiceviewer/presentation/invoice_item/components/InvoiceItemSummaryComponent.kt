package au.com.invoicetracker.invoiceviewer.presentation.invoice_item.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import au.com.invoicetracker.invoiceviewer.domain.models.Invoice
import au.com.invoicetracker.invoiceviewer.presentation.InvoiceSummary
import au.com.invoicetracker.invoiceviewer.presentation.invoice_list.toDisplayable

@Composable
fun InvoiceItemSummaryComponent(
    invoice: Invoice,
    modifier: Modifier = Modifier,
) {
    InvoiceSummary(invoice.toDisplayable().total, modifier = modifier)
}
