package com.xero.invoicetracker.invoiceviewer.presentation.invoice_item.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xero.invoicetracker.invoiceviewer.domain.models.Invoice
import com.xero.invoicetracker.invoiceviewer.presentation.InvoiceSummary
import com.xero.invoicetracker.invoiceviewer.presentation.invoice_list.toDisplayable

@Composable
fun InvoiceItemSummaryComponent(
    invoice: Invoice,
    modifier: Modifier = Modifier,
) {
    InvoiceSummary(invoice.toDisplayable().total, modifier = modifier)
}
