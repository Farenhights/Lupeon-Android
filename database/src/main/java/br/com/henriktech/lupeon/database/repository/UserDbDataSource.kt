package br.com.henriktech.lupeon.database.repository

import android.content.Context
import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.db.UserEntity
import br.com.henriktech.lupeon.database.db.dao.UserDao

class UserDbDataSource(context: Context) : UserRepository {
    private val userDao: UserDao by lazy {
        AppDataBase.getDatabase(context).userDao()
    }

    override fun createUser(userEntity: UserEntity) = userDao.save(userEntity)

    override fun getUser(userId: Int): UserEntity = userDao.getUser(userId)
}