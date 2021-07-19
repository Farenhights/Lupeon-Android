package br.com.henriktech.lupeon.api.repository

import br.com.henriktech.lupeon.api.model.LoginRequest
import br.com.henriktech.lupeon.api.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SecurityApi {
    @POST("Security/Login")
    suspend fun getLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>
}