package br.com.henriktech.lupeon.database.db

data class AlertEntity(
    val userId: Int,
    val icon: String,
    val link: String,
    val texto: String,
    val titulo: String
)