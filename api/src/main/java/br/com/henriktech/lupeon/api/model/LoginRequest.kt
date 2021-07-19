package br.com.henriktech.lupeon.api.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("Login")
    var login: String,
    @SerializedName("Password")
    var password: String
) {
    override fun toString(): String {
        return "{\"Login\":'$login', \"Password\":'$password'}"
    }
}