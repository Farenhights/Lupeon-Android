package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransporterFilter(
    @SerializedName("TransportadoraId")
    val id: Int,
    @SerializedName("Transportadora")
    val name: String,
): Parcelable