package br.com.henriktech.lupeon.api.repository

import android.content.Context
import br.com.henriktech.lupeon.api.model.LoginRequest
import br.com.henriktech.lupeon.api.network.BaseRepository
import org.json.JSONObject

class SecurityRepository(val context: Context, private val securityApi: SecurityApi) :
    BaseRepository() {

    suspend fun enterLogin(user: String, password: String) =
        safeCallApi { securityApi.postLogin(JSONObject(LoginRequest(user, password).toString())) }
}