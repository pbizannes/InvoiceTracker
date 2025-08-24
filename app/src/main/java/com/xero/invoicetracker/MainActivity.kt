package com.xero.invoicetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.xero.invoicetracker.invoiceviewer.presentation.InvoiceListDetailPane
import com.xero.invoicetracker.invoiceviewer.presentation.InvoiceViewModel
import com.xero.invoicetracker.ui.theme.InvoiceTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: InvoiceViewModel by viewModels()
        setContent {
            LaunchedEffect(true) {
                viewModel.loadInvoices()
            }
            InvoiceTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InvoiceListDetailPane(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
