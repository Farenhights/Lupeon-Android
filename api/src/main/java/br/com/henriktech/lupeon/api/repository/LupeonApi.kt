package br.com.henriktech.lupeon.api.repository

import br.com.henriktech.lupeon.api.model.request.*
import br.com.henriktech.lupeon.api.model.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface LupeonApi {

    // Security API
    @Headers("Content-Type: application/json")
    @POST("/Security/Login")
    suspend fun postLogin(@Body body: LoginRequest): Response<Login>

    @Headers("Content-Type: application/json")
    @POST("/Security/TokenNovaSenha")
    suspend fun postTokenNovaSenha(@Body body: NewPasswordRequest): Response<Message>

    @Headers("Content-Type: application/json")
    @POST("/Security/RedefinirSenha")
    suspend fun postRedefinirSenha(@Body body: RedefinePasswordRequest): Response<Message>

    // Shipper API
    @Headers("Content-Type: application/json")
    @POST("/Embarcador/Indicadores")
    suspend fun postEmbacadorIndicadores(
        @Header("Authorization") token: String,
        @Body body: ShipperRequest
    ): Response<Indicadores>

    @Headers("Content-Type: application/json")
    @POST("/Embarcador/Tracking")
    suspend fun postEmbacadorTracking(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int,
        @Body body: TrackingRequest
    ): Response<List<NFe>>

    @Headers("Content-Type: application/json")
    @POST("/Embarcador/SimulacaoOnline")
    suspend fun postEmbacadorSimulacaoOnline(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int,
        @Body body: Filial
    ): Response<Simulacao>

}