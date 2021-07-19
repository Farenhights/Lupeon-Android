package br.com.henriktech.lupeon.api.model

import android.view.Menu
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Token")
    val token: String?,
    @SerializedName("TipoUsuario")
    val userType: String?,
    @SerializedName("Menus")
    val menus: List<Menu>?,
    @SerializedName("Alertas")
    val alerts: List<Alert>?,
    @SerializedName("TokenType")
    val tokenType: String?,
    @SerializedName("ExpiresIn")
    val expiresIn: Int
)