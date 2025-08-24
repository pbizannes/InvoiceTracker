package com.xero.invoicetracker.invoiceviewer.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InvoiceResponse(
    @SerialName("items")
    val invoices: List<Invoice>
)
