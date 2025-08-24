package com.xero.invoicetracker.invoiceviewer.presentation.invoice_list

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xero.invoicetracker.invoiceviewer.domain.models.Invoice
import com.xero.invoicetracker.invoiceviewer.domain.models.InvoiceItem
import com.xero.invoicetracker.ui.theme.InvoiceTrackerTheme

@Composable
fun InvoiceComponent(
    invoice: DisplayableInvoice,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = invoice.date,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = contentColor
        )
        Spacer(modifier = Modifier.weight(1f))
        if (invoice.description != null) {
            Text(
                text = invoice.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = "$ ${invoice.total}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor,
        )
    }
}

@PreviewLightDark
@Composable
private fun InvoiceItemScreenPreview() {
    val item1 = InvoiceItem (
        id = "1",
        name = "Sample Item",
        quantity = 2,
        priceInCents = 1099,
    )
    val item2 = InvoiceItem (
        id = "2",
        name = "Sample Item 2",
        quantity = 1,
        priceInCents = 125,
    )
    val item3 = InvoiceItem (
        id = "3",
        name = "Sample Item 3",
        quantity = 3,
        priceInCents = 1099,
    )

    val sampleInvoice = Invoice(
        id = "1",
        date = "1st January",
        description = "Shopping",
        items = listOf(item1, item2, item3)
    )

    InvoiceTrackerTheme {
        InvoiceComponent(
            invoice = sampleInvoice.toDisplayable(),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
        )
    }
}
