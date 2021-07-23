package br.com.henriktech.lupeon.api.repository

import br.com.henriktech.lupeon.api.model.LoginResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SecurityApi {
    @POST("/Security/Login")
    suspend fun postLogin(@Body jSONObject: JSONObject): Response<LoginResponse>

    @POST("/Security/RefreshToken")
    suspend fun postRefreshToken(@Body jSONObject: JSONObject)

    @POST("/Security/NovaSenha")
    suspend fun postNovaSenha(@Body jSONObject: JSONObject)

    @POST("/Security/EsqueciSenha")
    suspend fun postEsqueciSenha(@Body jSONObject: JSONObject)
}