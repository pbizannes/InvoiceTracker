package au.com.invoicetracker.di

import au.com.invoicetracker.BuildConfig
import au.com.invoicetracker.invoiceviewer.data.source.network.InvoiceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun provideBaseUrl(): String = BuildConfig.INVOICE_BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideInvoiceService(retrofit: Retrofit): InvoiceService = retrofit.create(InvoiceService::class.java)
}