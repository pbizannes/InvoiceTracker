package com.xero.invoicetracker.invoiceviewer.data.source.network

import com.xero.invoicetracker.invoiceviewer.domain.models.InvoiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface InvoiceService {
    @GET("invoices.json")
    suspend fun getInvoices(): Response<InvoiceResponse>
}
