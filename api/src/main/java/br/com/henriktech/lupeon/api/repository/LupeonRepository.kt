package br.com.henriktech.lupeon.api.repository

import android.content.Context
import br.com.henriktech.lupeon.api.model.request.*
import br.com.henriktech.lupeon.api.network.BaseRepository

class LupeonRepository(val context: Context, private val lupeonApi: LupeonApi) :
    BaseRepository() {

    suspend fun enterLogin(user: String, password: String) = safeCallApi {
        lupeonApi.postLogin(LoginRequest(user, password))
    }

    suspend fun redefinePassword(token: String, password: String, passwordConfirm: String) =
        safeCallApi {
            lupeonApi.postRedefinirSenha(RedefinePasswordRequest(token, password, passwordConfirm))
        }

    suspend fun newPassword(email: String) =
        safeCallApi {
            lupeonApi.postTokenNovaSenha(NewPasswordRequest(email))
        }

    suspend fun indicatorsShipper(
        token: String,
        shipperId: Int,
        companyId: Int,
        timeCourseId: Int
    ) =
        safeCallApi {
            lupeonApi.postEmbacadorIndicadores(
                token,
                companyId,
                ShipperRequest(shipperId, timeCourseId)
            )
        }

    suspend fun indicatorsTransporter(
        token: String,
        transporterId: Int,
        companyId: Int,
        timeCourseId: Int
    ) =
        safeCallApi {
            lupeonApi.postTransportadorIndicadores(
                token,
                companyId,
                TransporterRequest(transporterId, timeCourseId)
            )
        }

    suspend fun indicatorsDriver(
        token: String,
        driverId: Int,
        companyId: Int,
        timeCourseId: Int
    ) =
        safeCallApi {
            lupeonApi.postMotoristaIndicadores(
                token,
                companyId,
                DriverRequest(driverId, timeCourseId)
            )
        }

    suspend fun trackingShipper(token: String, companyId: Int, cnpj: String, numberNFe: Int) =
        safeCallApi {
            lupeonApi.postEmbacadorTracking(token, companyId, TrackingRequest(cnpj, numberNFe))
        }
}