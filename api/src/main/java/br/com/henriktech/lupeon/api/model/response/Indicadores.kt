package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Indicadores(
    @SerializedName("data")
    val indicadores: List<Indicador>,
    val status: String
): Parcelable