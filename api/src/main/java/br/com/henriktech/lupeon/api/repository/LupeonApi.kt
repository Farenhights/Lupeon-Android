package br.com.henriktech.lupeon.api.repository

import br.com.henriktech.lupeon.api.model.request.EmbarcadorRequest
import br.com.henriktech.lupeon.api.model.request.LoginRequest
import br.com.henriktech.lupeon.api.model.response.Indicadores
import br.com.henriktech.lupeon.api.model.response.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface LupeonApi {
    /**
     * Security
     * */
    @Headers("Content-Type: application/json")
    @POST("/Security/Login")
    suspend fun postLogin(@Body body: LoginRequest): Response<Login>

    /**
     * Embarcador
     * */
    @Headers("Content-Type: application/json")
    @POST("/Embarcador/Indicadores")
    suspend fun postEmbacadorIndicadores(
        @Header("Authorization") token: String,
        @Body body: EmbarcadorRequest
    ): Response<Indicadores>
}