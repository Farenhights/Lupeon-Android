package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toUser
import br.com.henriktech.lupeon.database.db.UserEntity
import br.com.henriktech.lupeon.database.db.dao.UserDao

class UserDbDataSource(private val userDao: UserDao) : UserRepository {

    override fun createUser(userEntity: UserEntity) = userDao.save(userEntity)

    override fun getUser(userId: Int): User = userDao.getUser(userId).toUser()
}