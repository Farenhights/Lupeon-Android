package br.com.henriktech.lupeon.api.repository

import br.com.henriktech.lupeon.api.model.Login
import br.com.henriktech.lupeon.api.model.LoginRequest
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SecurityApi {

    @Headers("Content-Type: application/json")
    @POST("/Security/Login")
    suspend fun postLogin(@Body body: LoginRequest): Response<Login>

    @POST("/Security/RefreshToken")
    suspend fun postRefreshToken(@Body jSONObject: JSONObject)

    @POST("/Security/NovaSenha")
    suspend fun postNovaSenha(@Body jSONObject: JSONObject)

    @POST("/Security/EsqueciSenha")
    suspend fun postEsqueciSenha(@Body jSONObject: JSONObject)
}