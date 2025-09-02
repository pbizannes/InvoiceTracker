package au.com.invoicetracker.di

import au.com.invoicetracker.invoiceviewer.data.source.DefaultInvoiceRepository
import au.com.invoicetracker.invoiceviewer.data.source.network.InvoiceService
import au.com.invoicetracker.invoiceviewer.domain.repository.InvoiceRepository
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