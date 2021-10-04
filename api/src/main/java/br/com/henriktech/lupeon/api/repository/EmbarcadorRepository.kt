package br.com.henriktech.lupeon.api.repository

import android.content.Context
import br.com.henriktech.lupeon.api.model.request.EmbarcadorRequest
import br.com.henriktech.lupeon.api.network.BaseRepository

class EmbarcadorRepository(val context: Context, private val embarcadorApi: EmbarcadorApi) :
    BaseRepository() {

    suspend fun postIndicadores(token: String, id: Int, period: Int) {
        embarcadorApi.postIndicadores(
            token = token,
            EmbarcadorRequest(EmbarcadorId = id, PeriodoId = period)
        )
    }
}
