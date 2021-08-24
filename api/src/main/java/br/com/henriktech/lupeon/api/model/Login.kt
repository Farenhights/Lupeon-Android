package br.com.henriktech.lupeon.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    val Alertas: List<Alerta>,
    val ExpiresIn: Int,
    val Menus: List<Menu>,
    val TipoUsuario: String,
    val Token: String
) : Parcelable