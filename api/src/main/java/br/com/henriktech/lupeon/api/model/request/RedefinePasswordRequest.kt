package br.com.henriktech.lupeon.api.model.request

import com.google.gson.annotations.SerializedName

data class RedefinePasswordRequest(
    @SerializedName("Token")
    val token: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("PasswordConfirm")
    val passwordConfirm: String
)