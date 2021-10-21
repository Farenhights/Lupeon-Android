package br.com.henriktech.lupeon.api.repository

import android.content.Context
import br.com.henriktech.lupeon.api.model.request.EmbarcadorRequest
import br.com.henriktech.lupeon.api.model.request.LoginRequest
import br.com.henriktech.lupeon.api.network.BaseRepository

class LupeonRepository(val context: Context, private val lupeonApi: LupeonApi) :
    BaseRepository() {

    suspend fun enterLogin(user: String, password: String) = safeCallApi {
        lupeonApi.postLogin(LoginRequest(login = user, password = password))
    }

    suspend fun postEmbarcadorIndicadores(token: String, shipperId: Int, periodId: Int) = safeCallApi {
        lupeonApi.postEmbacadorIndicadores(
            token = token,
            EmbarcadorRequest(EmbarcadorId = shipperId, PeriodoId = periodId)
        )
    }
}