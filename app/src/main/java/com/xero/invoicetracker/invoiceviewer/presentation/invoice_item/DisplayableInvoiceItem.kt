package com.xero.invoicetracker.invoiceviewer.presentation.invoice_item

import com.xero.invoicetracker.invoiceviewer.domain.models.InvoiceItem
import com.xero.invoicetracker.invoiceviewer.util.formatCents

data class DisplayableInvoiceItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val price: String,
    val total: String,
)

fun InvoiceItem.toDisplayable(): DisplayableInvoiceItem {
    return DisplayableInvoiceItem(
        id = id,
        name = name,
        quantity = quantity,
        price = priceInCents.formatCents(),
        total = (quantity * priceInCents).formatCents()
    )
}
