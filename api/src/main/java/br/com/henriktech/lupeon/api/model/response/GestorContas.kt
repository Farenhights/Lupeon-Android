package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GestorContas(
    @SerializedName("NomeGestor")
    val nomeGestor: String,
    val contato: String
): Parcelable