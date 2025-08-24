package com.xero.invoicetracker.invoiceviewer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.xero.invoicetracker.ui.theme.InvoiceTrackerTheme

@Composable
fun ErrorComponent(
    error: String,
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
            text = error,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = contentColor
        )
    }
}

@PreviewLightDark
@Composable
private fun ErrorComponentPreview() {
    InvoiceTrackerTheme {
        ErrorComponent(
            error = "No network",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
        )
    }
}
