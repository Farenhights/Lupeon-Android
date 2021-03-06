package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.db.UserEntity

class UserDbDataSource(private val dataBase: AppDataBase) : UserRepository {

    override suspend fun createUser(userEntity: UserEntity) = dataBase.userDao().save(userEntity)

    override suspend fun getUser() = dataBase.userDao().getUser()

    override suspend fun delete() = dataBase.userDao().delete()

}