package br.com.henriktech.lupeon.api.repository

import br.com.henriktech.lupeon.api.model.request.LoginRequest
import br.com.henriktech.lupeon.api.model.response.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SecurityApi {

    @Headers("Content-Type: application/json")
    @POST("/Security/Login")
    suspend fun postLogin(@Body body: LoginRequest): Response<Login>

}