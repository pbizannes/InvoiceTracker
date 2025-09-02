package au.com.invoicetracker.invoiceviewer.presentation.invoice_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import au.com.invoicetracker.invoiceviewer.domain.models.Invoice
import au.com.invoicetracker.invoiceviewer.domain.models.InvoiceItem
import au.com.invoicetracker.invoiceviewer.presentation.components.EmptyComponent
import au.com.invoicetracker.invoiceviewer.presentation.components.ErrorComponent
import au.com.invoicetracker.invoiceviewer.presentation.InvoiceListState
import au.com.invoicetracker.invoiceviewer.presentation.invoice_list.components.InvoiceComponent
import au.com.invoicetracker.invoiceviewer.presentation.invoice_list.components.InvoiceListSummaryComponent
import au.com.invoicetracker.ui.theme.InvoiceTrackerTheme

@Composable
fun InvoiceListScreen(
    invoiceListState: InvoiceListState,
    onItemClick: (Invoice) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (invoiceListState.isLoading) {
        Box(
            modifier = modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        when {
            invoiceListState.error != null -> ErrorComponent(invoiceListState.error)
            else -> InvoiceList(invoiceListState.invoiceList, onItemClick)
        }
    }
}

@Composable
fun InvoiceList(
    invoiceList: List<Invoice>,
    onItemClick: (Invoice) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (invoiceList.isEmpty()) {
        EmptyComponent()
    } else {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header Composable
            item(key = "Invoice Header") {
                Text(
                    text = "Invoices",
                    style = MaterialTheme.typography.headlineMedium,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
            }
            // Invoice List
            if (invoiceList.isEmpty()) {
                item(key = "Invoice Empty") {
                    EmptyComponent()
                    HorizontalDivider()
                }
            } else items(
                invoiceList,
                key = { item -> item.id }
            ) { invoice ->
                InvoiceComponent(
                    invoice.toDisplayable(),
                    modifier = modifier.clickable { onItemClick(invoice) })
                HorizontalDivider()
            }
            item(key = "Invoice Total") {
                InvoiceListSummaryComponent()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun InvoiceListScreenPreviewWithData() {
    val sampleInvoices = listOf(
        Invoice(
            id = "1",
            date = "1st January",
            description = "Shopping",
            items = listOf(
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
            ),
        ),
        Invoice(
            id = "2",
            date = "2nd January",
            description = "Shopping Again",
            items = listOf(
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
            ),
        ),
        Invoice(
            id = "3",
            date = "3rd January",
            description = "Shopping Yet Again",
            items = listOf(
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
            ),
        ),
    )

    val sampleInvoiceListState = InvoiceListState(
        error = null,
        isLoading = false,
        invoiceList = sampleInvoices,
    )

    InvoiceTrackerTheme {
        InvoiceListScreen(
            invoiceListState = sampleInvoiceListState,
            onItemClick = { },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
        )
    }
}

@PreviewLightDark
@Composable
private fun InvoiceListScreenPreviewWithoutData() {
    val sampleInvoiceListState = InvoiceListState(
        error = null,
        isLoading = false,
        invoiceList = listOf(),
    )

    InvoiceTrackerTheme {
        InvoiceListScreen(
            invoiceListState = sampleInvoiceListState,
            onItemClick = { },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
        )
    }
}
