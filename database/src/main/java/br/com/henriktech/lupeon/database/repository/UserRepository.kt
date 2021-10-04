package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun createUser(userEntity: UserEntity)

    suspend fun getUser(): UserEntity

    suspend fun delete(userEntity: UserEntity)

}