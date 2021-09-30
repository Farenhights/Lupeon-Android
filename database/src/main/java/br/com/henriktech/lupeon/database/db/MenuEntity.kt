package br.com.henriktech.lupeon.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuEntity(
    val userId: Int,
    @PrimaryKey
    val option: String,
    val visible: Boolean
)