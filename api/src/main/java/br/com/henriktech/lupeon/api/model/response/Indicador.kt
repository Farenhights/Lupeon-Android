package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Indicador(
    val Indicador: String,
    val Valor: String
) : Parcelable