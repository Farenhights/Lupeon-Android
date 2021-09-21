package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.api.model.Login
import br.com.henriktech.lupeon.data.model.User

interface UserRepository {

    fun createUser(login: Login)

    fun getUser(userId: Int): User

}