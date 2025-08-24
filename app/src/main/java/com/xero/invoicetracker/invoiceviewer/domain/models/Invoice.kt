package com.xero.invoicetracker.invoiceviewer.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Parcelize
@Serializable
data class Invoice(
    val id: String,
    val date: String,
    val description: String? = null,
    val items: List<InvoiceItem>
) : Parcelable

@Parcelize
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class InvoiceItem (
    val id: String,
    val name: String,
    val quantity: Int,
    @JsonNames("priceinCents", "priceInCents")
    val priceInCents: Int // Changed to camelCase for Kotlin convention
) : Parcelable