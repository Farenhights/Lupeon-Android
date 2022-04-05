package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Invoice(
    val DataEmissaoNFe: String,
    val Destinatario: String,
    val NumeroNfe: String
) : Parcelable