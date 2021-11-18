package br.com.henriktech.lupeon.api.model.request

import com.google.gson.annotations.SerializedName

data class NewPasswordRequest(
    @SerializedName("Email")
    val email: String
)