package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ocorrencia(
    var DataOcorrencia: String,
    var OcorrenciaLupeon: String,
    var OcorrenciaTransportadora: String
) : Parcelable