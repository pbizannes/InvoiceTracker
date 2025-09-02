package au.com.invoicetracker.invoiceviewer.presentation.invoice_item.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import au.com.invoicetracker.invoiceviewer.presentation.invoice_item.DisplayableInvoiceItem
import au.com.invoicetracker.ui.theme.InvoiceTrackerTheme

@Composable
fun InvoiceItemComponent(
    displayableInvoiceItem: DisplayableInvoiceItem,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = displayableInvoiceItem.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$ ${displayableInvoiceItem.price}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = contentColor
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = displayableInvoiceItem.quantity.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = contentColor
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$ ${displayableInvoiceItem.total}",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = contentColor,
            modifier = modifier
                .defaultMinSize(40.dp)
                ,
        )
    }
}

val sampleInvoice = DisplayableInvoiceItem(
    id = "1",
    name = "Sample Item",
    quantity = 2,
    price = "10.99",
    total = "21.98"
)

@PreviewLightDark
@Composable
private fun InvoiceItemComponentPreview() {
    InvoiceTrackerTheme {
        InvoiceItemComponent(
            displayableInvoiceItem = sampleInvoice,
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}
