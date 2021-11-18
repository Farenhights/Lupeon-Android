package br.com.henriktech.lupeon.api.repository

import android.content.Context
import br.com.henriktech.lupeon.api.model.request.*
import br.com.henriktech.lupeon.api.network.BaseRepository

class LupeonRepository(val context: Context, private val lupeonApi: LupeonApi) :
    BaseRepository() {

    suspend fun enterLogin(user: String, password: String) = safeCallApi {
        lupeonApi.postLogin(LoginRequest(user, password))
    }

    suspend fun redefinePassword(token: String, password: String, passwordConfirm: StrictMath) =
        safeCallApi {
            lupeonApi.postRedefinirSenha(RedefinePasswordRequest(token, password, passwordConfirm))
        }

    suspend fun newPassword(email: String) =
        safeCallApi {
            lupeonApi.postTokenNovaSenha(NewPasswordRequest(email))
        }

    suspend fun indicatorsShipper(token: String, shipperId: Int, timeCourseId: Int) =
        safeCallApi {
            lupeonApi.postEmbacadorIndicadores(token, ShipperRequest(shipperId, timeCourseId))
        }

    suspend fun trackingShipper(token: String, companyId: Int, cnpj: String, numberNFe: Int) =
        safeCallApi {
            lupeonApi.postEmbacadorTracking(token, companyId, TrackingRequest(cnpj, numberNFe))
        }
}