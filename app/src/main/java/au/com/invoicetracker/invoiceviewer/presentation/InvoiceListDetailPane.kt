package au.com.invoicetracker.invoiceviewer.presentation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import au.com.invoicetracker.invoiceviewer.domain.models.Invoice
import au.com.invoicetracker.invoiceviewer.presentation.invoice_item.InvoiceItemDetailScreen
import au.com.invoicetracker.invoiceviewer.presentation.invoice_list.InvoiceListScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun InvoiceListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: InvoiceViewModel = hiltViewModel(),
) {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<Invoice>()
    val scope = rememberCoroutineScope()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    NavigableListDetailPaneScaffold(
        navigator = scaffoldNavigator,
        listPane = {
            AnimatedPane {
                InvoiceListScreen(
                    state,
                    onItemClick = { invoice ->
                        scope.launch {
                            scaffoldNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail, invoice)
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                // Show the detail pane content if selected item is available
                scaffoldNavigator.currentDestination?.contentKey?.let { invoice ->
                    InvoiceItemDetailScreen(invoice)
                }
            }
        },
    )
}
