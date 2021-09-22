package br.com.henriktech.lupeon.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    var menuId: Int,
    val userId: Int,
    val option: String,
    val visible: Boolean
) {
    constructor(userId: Int, option: String, visible: Boolean) :
            this(0, userId, option, visible)
}