package br.com.henriktech.lupeon.api.model

import com.google.gson.annotations.SerializedName

data class Alert(
    @SerializedName("icon")
    var icon: String?,
    @SerializedName("titulo")
    var title: String?,
    @SerializedName("texto")
    var text: String,
    @SerializedName("link")
    var link: String
)