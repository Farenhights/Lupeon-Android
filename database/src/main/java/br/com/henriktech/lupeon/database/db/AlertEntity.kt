package br.com.henriktech.lupeon.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alert")
data class AlertEntity(
    val userId: Int,
    val icon: String,
    val link: String,
    val text: String,
    @PrimaryKey
    val title: String
)