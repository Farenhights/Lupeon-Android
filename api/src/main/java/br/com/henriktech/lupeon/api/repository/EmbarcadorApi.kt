package br.com.henriktech.lupeon.api.repository

import br.com.henriktech.lupeon.api.model.request.EmbarcadorRequest
import br.com.henriktech.lupeon.api.model.response.Indicadores
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface EmbarcadorApi {

    @Headers("Content-Type: application/json")
    @POST("/Embarcador/Indicadores")
    suspend fun postIndicadores(
        @Header("Authorization") token: String,
        @Body body: EmbarcadorRequest
    ): Response<Indicadores>
}