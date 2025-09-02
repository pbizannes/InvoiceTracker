package au.com.invoicetracker.invoiceviewer.data.source.network

import au.com.invoicetracker.BuildConfig
import au.com.invoicetracker.invoiceviewer.domain.models.InvoiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface InvoiceService {
    @GET(BuildConfig.INVOICE_PATH)
    suspend fun getInvoices(): Response<InvoiceResponse>
}
