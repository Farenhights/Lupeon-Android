package br.com.henriktech.lupeon.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Alerta(
    val icon: String,
    val link: String,
    val texto: String,
    val titulo: String
): Parcelable