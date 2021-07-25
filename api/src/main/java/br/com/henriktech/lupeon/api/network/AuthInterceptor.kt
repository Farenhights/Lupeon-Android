package br.com.henriktech.lupeon.api.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().build()
        request = request.newBuilder()
            .url(url)
            .build()
        val response = chain.proceed(request)
        Timber.i(response.message)
        return response
    }
}