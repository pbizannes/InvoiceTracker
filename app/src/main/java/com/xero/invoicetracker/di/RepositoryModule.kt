package com.xero.invoicetracker.di

import com.xero.invoicetracker.invoiceviewer.data.source.DefaultInvoiceRepository
import com.xero.invoicetracker.invoiceviewer.data.source.network.InvoiceService
import com.xero.invoicetracker.invoiceviewer.domain.repository.InvoiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideInvoiceRepository(service: InvoiceService): InvoiceRepository =
        DefaultInvoiceRepository(service)
}