package com.xero.invoicetracker.invoiceviewer.domain.repository

import com.xero.invoicetracker.invoiceviewer.domain.models.Invoice

/**
 * Interface that represents a Repository for getting [List<Invoice>] related data.
 */
interface InvoiceRepository {
    /**
     * Get a List of [List<Invoice>].
     */
    suspend fun getInvoices(): Result<List<Invoice>>
}