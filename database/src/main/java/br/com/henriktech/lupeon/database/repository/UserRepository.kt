package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.UserEntity

interface UserRepository {

    fun createUser(userEntity: UserEntity)

    fun getUser(userId: Int): UserEntity

}