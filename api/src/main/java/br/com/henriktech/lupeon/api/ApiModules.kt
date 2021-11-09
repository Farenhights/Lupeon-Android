package br.com.henriktech.lupeon.api

import br.com.henriktech.lupeon.api.network.AuthInterceptor
import br.com.henriktech.lupeon.api.repository.LupeonApi
import br.com.henriktech.lupeon.api.repository.LupeonRepository
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiModules {

    private const val API_URL = "http://apimobile.lupeon.com.br"

    private val moduleDI = module {
        single { LupeonRepository(get(), get()) }
    }

    private val networkModule = module {
        factory { AuthInterceptor() }
        factory { provideOkHttpClient(get()) }
        factory { provideLupeonApi(get()) }
        single { provideRetrofit(get()) }
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun provideLupeonApi(retrofit: Retrofit) = retrofit.create(LupeonApi::class.java)

    private fun provideOkHttpClient(authInterceptor: AuthInterceptor) =
        OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()

    fun loadModule() {
        loadKoinModules(
            listOf(
                moduleDI, networkModule
            )
        )
    }
}