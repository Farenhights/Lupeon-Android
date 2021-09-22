package br.com.henriktech.lupeon.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alert")
data class AlertEntity(
    @PrimaryKey(autoGenerate = true)
    val alertId: Int,
    val userId: Int,
    val icon: String,
    val link: String,
    val text: String,
    val title: String
) {
    constructor(userId: Int, icon: String, link: String, text: String, title: String) :
            this(0, userId, icon, link, text, title)
}