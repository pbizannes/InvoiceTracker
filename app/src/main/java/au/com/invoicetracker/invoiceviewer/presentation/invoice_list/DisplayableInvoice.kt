package au.com.invoicetracker.invoiceviewer.presentation.invoice_list

import android.os.Build
import au.com.invoicetracker.invoiceviewer.domain.models.Invoice
import au.com.invoicetracker.invoiceviewer.util.formatCents
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

data class DisplayableInvoice(
    val id: String,
    val date: String,
    val description: String? = null,
    val total: String
)

fun Invoice.toDisplayable(): DisplayableInvoice {
    val formattedDate =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val formatter = DateTimeFormatter.ofPattern("hh:mm dd-MM-yyyy")
                val localDateTime = LocalDateTime.parse(date)
                formatter.format(localDateTime)
            } catch (_: DateTimeParseException) {
                date
            }
        } else {
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US)
                val convertedDate = dateFormat.parse(date)
                if (convertedDate == null) throw IllegalArgumentException()

                val outputSimpleDateFormat = SimpleDateFormat("hh:mm dd-MM-yyyy", Locale.US)
                outputSimpleDateFormat.format(convertedDate)
            } catch (_: IllegalArgumentException) {
                date
            }
        }

    val totalInvoiceCost = items.sumOf { item -> item.priceInCents * item.quantity }

    return DisplayableInvoice(
        id = id,
        date = formattedDate,
        description = description,
        total = totalInvoiceCost.formatCents()
    )
}
