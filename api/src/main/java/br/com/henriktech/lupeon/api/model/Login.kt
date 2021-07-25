package br.com.henriktech.lupeon.api.model

data class Login(
    val Alertas: List<Alerta>,
    val ExpiresIn: Int,
    val Menus: List<Menu>,
    val TipoUsuario: String,
    val Token: String
)