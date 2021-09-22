package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.UserEntity

interface UserRepository {

    suspend fun createUser(userEntity: UserEntity)

    suspend fun getUser(userId: Int): UserEntity

}