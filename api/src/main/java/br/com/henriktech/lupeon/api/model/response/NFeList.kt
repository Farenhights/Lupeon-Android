package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NFeList(
    @SerializedName("data")
    val nFesList: List<NFe>,
    val status: String,
) : Parcelable