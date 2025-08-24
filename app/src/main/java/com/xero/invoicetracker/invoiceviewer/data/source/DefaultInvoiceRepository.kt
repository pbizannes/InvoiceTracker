package com.xero.invoicetracker.invoiceviewer.data.source

import com.xero.invoicetracker.exceptions.InvoiceRetrievalException
import com.xero.invoicetracker.invoiceviewer.data.source.network.InvoiceService
import com.xero.invoicetracker.invoiceviewer.domain.models.Invoice
import com.xero.invoicetracker.invoiceviewer.domain.repository.InvoiceRepository

class DefaultInvoiceRepository(val invoiceService: InvoiceService): InvoiceRepository {
    override suspend fun getInvoices(): Result<List<Invoice>> {
        return try {
            val response = invoiceService.getInvoices()

            val responseBody = response.body()
            if (response.isSuccessful) {
                if (responseBody != null) {
                    Result.success(responseBody.invoices)
                } else {
                    Result.failure(NoSuchElementException())
                }
            } else {
                Result.failure(InvoiceRetrievalException("${response.code()}"))
            }
        } catch (error: Exception) {
            Result.failure(InvoiceRetrievalException(error))
        }
    }
}