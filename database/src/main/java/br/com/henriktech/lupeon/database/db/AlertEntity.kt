package br.com.henriktech.lupeon.database.db

import androidx.room.Entity

@Entity(tableName = "alert")
data class AlertEntity(
    val userId: Int,
    val icon: String,
    val link: String,
    val text: String,
    val title: String
)