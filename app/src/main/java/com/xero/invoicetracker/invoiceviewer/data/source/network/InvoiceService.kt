package com.xero.invoicetracker.invoiceviewer.data.source.network

import com.xero.invoicetracker.BuildConfig
import com.xero.invoicetracker.invoiceviewer.domain.models.InvoiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface InvoiceService {
    @GET(BuildConfig.INVOICE_PATH)
    suspend fun getInvoices(): Response<InvoiceResponse>
}
