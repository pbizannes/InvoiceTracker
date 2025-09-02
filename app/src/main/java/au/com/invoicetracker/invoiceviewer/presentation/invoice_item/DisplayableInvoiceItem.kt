package au.com.invoicetracker.invoiceviewer.presentation.invoice_item

import au.com.invoicetracker.invoiceviewer.domain.models.InvoiceItem
import au.com.invoicetracker.invoiceviewer.util.formatCents

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
