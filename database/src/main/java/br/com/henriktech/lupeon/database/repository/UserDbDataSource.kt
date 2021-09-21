package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.db.UserEntity

class UserDbDataSource(private val dataBase: AppDataBase) : UserRepository {

    override fun createUser(userEntity: UserEntity) = dataBase.userDao().save(userEntity)

    override fun getUser(userId: Int): UserEntity = dataBase.userDao().getUser(userId)
}