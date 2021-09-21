package br.com.henriktech.lupeon.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val userId: Int,
    val accessToken: String,
    val companyId: String,
    val email: String,
    val expiresIn: Int,
    val login: String,
    val name: String,
    val userType: String,
    val tokenType: String,
    val nameManager: String,
    val contactsManager: String
)
