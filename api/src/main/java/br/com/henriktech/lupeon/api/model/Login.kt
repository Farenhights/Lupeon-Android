package br.com.henriktech.lupeon.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    @SerializedName("AccessToken")
    val accessToken: String,
    @SerializedName("Alertas")
    val alertas: List<Alerta>,
    @SerializedName("CompanyId")
    val companyId: String,
    @SerializedName("Email")
    val email: String,
    @SerializedName("ExpiresIn")
    val expiresIn: Int,
    @SerializedName("GestorContas")
    val gestorContas: GestorContas,
    @SerializedName("Login")
    val login: String,
    @SerializedName("Menus|")
    val menus: List<Menu>,
    @SerializedName("Nome")
    val nome: String,
    @SerializedName("TipoUsuario")
    val tipoUsuario: String,
    @SerializedName("TokenType")
    val tokenType: String,
    @SerializedName("UsuarioId")
    val usuarioId: Int
): Parcelable