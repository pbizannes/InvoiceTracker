package au.com.invoicetracker.invoiceviewer.util

import java.text.NumberFormat
import java.util.Locale

fun Int.formatCents(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return formatter.format(this / 100f)
}