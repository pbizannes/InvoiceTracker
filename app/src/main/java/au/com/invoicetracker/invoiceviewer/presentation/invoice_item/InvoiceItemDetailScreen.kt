package au.com.invoicetracker.invoiceviewer.presentation.invoice_item

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import au.com.invoicetracker.invoiceviewer.domain.models.Invoice
import au.com.invoicetracker.invoiceviewer.domain.models.InvoiceItem
import au.com.invoicetracker.invoiceviewer.presentation.components.EmptyComponent
import au.com.invoicetracker.invoiceviewer.presentation.invoice_item.components.InvoiceItemComponent
import au.com.invoicetracker.invoiceviewer.presentation.invoice_item.components.InvoiceItemSummaryComponent
import au.com.invoicetracker.ui.theme.InvoiceTrackerTheme

@Composable
fun InvoiceItemDetailScreen(
    invoice: Invoice,
    modifier: Modifier = Modifier,
) {
    if (invoice.items.isEmpty()) EmptyComponent()
    else LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(key = "Invoice Item Header") {
            Text(
                text = "Invoice Items",
                style = MaterialTheme.typography.headlineMedium,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(
            invoice.items.map { it.toDisplayable() },
            key = { item -> item.id }
        ) { displayableInvoiceItem ->
            InvoiceItemComponent(
                displayableInvoiceItem = displayableInvoiceItem,
                modifier = Modifier.fillMaxWidth()
            )
            HorizontalDivider()
        }
        item(key = "Invoice Item Footer") {
            InvoiceItemSummaryComponent(
                invoice = invoice, modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun InvoiceItemScreenPreview() {
    val sampleInvoiceItems = listOf(
        InvoiceItem(
            id = "1",
            name = "Sample Item",
            quantity = 2,
            priceInCents = 1099,
        ),
        InvoiceItem(
            id = "2",
            name = "Sample Item 2",
            quantity = 1,
            priceInCents = 125,
        ),
        InvoiceItem(
            id = "3",
            name = "Sample Item 3",
            quantity = 5,
            priceInCents = 258,
        ),
    )
    val sampleInvoice = Invoice(
        id = "1",
        date = "2023-08-01",
        description = "Sample Invoice",
        items = sampleInvoiceItems,
    )

    InvoiceTrackerTheme {
        InvoiceItemDetailScreen(
            invoice = sampleInvoice,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
        )
    }
}
