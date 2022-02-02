package br.com.henriktech.lupeon.api.network

import retrofit2.Response

open class BaseRepository {

    suspend fun <T> safeCallApi(call: suspend () -> Response<T>): ApiResult<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ApiResult.Success(response.body())
            } else {
                ApiResult.Error("ApiResult Error: ${response.message()}")
            }
        } catch (error: Exception) {
            ApiResult.Error("ApiResult Error: ${error.message}")
        }
    }
}