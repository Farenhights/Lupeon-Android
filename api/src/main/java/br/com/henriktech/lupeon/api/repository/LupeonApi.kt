package br.com.henriktech.lupeon.api.repository

import br.com.henriktech.lupeon.api.model.request.*
import br.com.henriktech.lupeon.api.model.response.*
import retrofit2.Response
import retrofit2.http.*

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
        @Header("CompanyId") companyId: Int,
        @Body body: ShipperRequest
    ): Response<Indicadores>

    @Headers("Content-Type: application/json")
    @POST("/Embarcador/Tracking")
    suspend fun postEmbacadorTracking(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int,
        @Body body: TrackingRequest
    ): Response<NFeList>

    @Headers("Content-Type: application/json")
    @POST("/Embarcador/SimulacaoOnline")
    suspend fun postEmbacadorSimulacaoOnline(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int,
        @Body body: Filial
    ): Response<Simulacao>

    // Driver API

    @Headers("Content-Type: application/json")
    @POST("/Motorista/Indicadores")
    suspend fun postMotoristaIndicadores(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int,
        @Body body: DriverRequest
    ): Response<Indicadores>

    @Headers("Content-Type: application/json")
    @POST("/Motorista/EnviarComprovante")
    suspend fun postMotoristaEnviarComprovante(
        @Header("Authorization") token: String,
        @Body body: FileRequest
    ): Response<Indicadores>

    @Headers("Content-Type: application/json")
    @POST("/Motorista/NotasEmTransito")
    suspend fun postMotoristaNotasEmTransito(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int,
        @Body body: NotasEmTransitoRequest
    ): Response<InvoiceList>

    @Headers("Content-Type: application/json")
    @POST("/Transportadora/Indicadores")
    suspend fun postTransportadorIndicadores(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int,
        @Body body: TransporterRequest
    ): Response<Indicadores>

    @Headers("Content-Type: application/json")
    @POST("/Transportadora/NotasEmTransito")
    suspend fun postTransportadorNotasEmTransito(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int,
        @Body body: InvoiceRequest
    ): Response<InvoiceList>

    // Filter API

    @Headers("Content-Type: application/json")
    @GET("/Filtro/Transportadoras")
    suspend fun getFiltroTransportadoras(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int
    ): Response<TransporterFilterList>

    @Headers("Content-Type: application/json")
    @GET("/Filtro/Periodo")
    suspend fun getFiltroPeriodo(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int
    ): Response<PeriodFilterList>

    @Headers("Content-Type: application/json")
    @GET("/Filtro/Filial")
    suspend fun getFiltroFilial(
        @Header("Authorization") token: String,
        @Header("CompanyId") companyId: Int
    ): Response<FilialFilterList>

    @Headers("Content-Type: application/json")
    @GET("/Filtro/Ocorrencias")
    suspend fun getFiltroOcorrencias(
        @Header("Authorization") token: String
    ): Response<OccurrenceFilterList>

}