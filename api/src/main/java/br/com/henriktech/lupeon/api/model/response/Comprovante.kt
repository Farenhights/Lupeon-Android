package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comprovante(
    var PossuiComprovante: Boolean
): Parcelable