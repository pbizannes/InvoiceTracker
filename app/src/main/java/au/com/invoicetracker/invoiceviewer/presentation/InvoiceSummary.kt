package au.com.invoicetracker.invoiceviewer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import au.com.invoicetracker.invoiceviewer.util.formatCents
import au.com.invoicetracker.ui.theme.InvoiceTrackerTheme

@Composable
fun InvoiceSummary(
    total: String,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Total",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$ $total",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor,
            modifier = modifier.defaultMinSize(40.dp)
        )
    }
}

@PreviewLightDark
@Composable
private fun InvoiceSummaryPreview() {
    InvoiceTrackerTheme {
        InvoiceSummary(
            total = 12345.formatCents(),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}
