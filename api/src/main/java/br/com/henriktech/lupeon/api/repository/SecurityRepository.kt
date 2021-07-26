package br.com.henriktech.lupeon.api.repository

import android.content.Context
import br.com.henriktech.lupeon.api.network.BaseRepository
import com.google.gson.annotations.SerializedName


class SecurityRepository(val context: Context, private val securityApi: SecurityApi) :
    BaseRepository() {

    suspend fun enterLogin(user: String, password: String) = safeCallApi {
        securityApi.postLogin(LoginRequest(login = user, password = password))
    }
}

data class LoginRequest(
    @SerializedName("Login")
    var login: String,
    @SerializedName("Password")
    var password: String
)