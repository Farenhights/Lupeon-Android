package br.com.henriktech.lupeon.database.db

import androidx.room.Entity

@Entity(tableName = "menu")
data class MenuEntity (
    val userId: Int,
    val option: String,
    val visible: Boolean
)