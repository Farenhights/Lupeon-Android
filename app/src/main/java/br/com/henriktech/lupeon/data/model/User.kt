package br.com.henriktech.lupeon.data.model

import br.com.henriktech.lupeon.api.model.Login
import br.com.henriktech.lupeon.database.db.UserEntity

data class User(
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

fun Login.toUserEntity(): UserEntity {
    return with(this) {
        UserEntity(
            userId = this.usuarioId,
            accessToken = this.accessToken,
            companyId = this.companyId,
            email = this.email,
            expiresIn = this.expiresIn,
            login = this.login,
            name = this.nome,
            userType = this.tipoUsuario,
            tokenType = this.tokenType,
            nameManager = this.gestorContas.nomeGestor,
            contactsManager = this.gestorContas.contato
        )
    }
}

fun User.toUserEntity(): UserEntity {
    return with(this) {
        UserEntity(
            userId = this.userId,
            accessToken = this.accessToken,
            companyId = this.companyId,
            email = this.email,
            expiresIn = this.expiresIn,
            login = this.login,
            name = this.name,
            userType = this.userType,
            tokenType = this.tokenType,
            nameManager = this.nameManager,
            contactsManager = this.contactsManager
        )
    }
}

fun UserEntity.toUser(): User {
    return with(this) {
        User(
            userId = this.userId,
            accessToken = this.accessToken,
            companyId = this.companyId,
            email = this.email,
            expiresIn = this.expiresIn,
            login = this.login,
            name = this.name,
            userType = this.userType,
            tokenType = this.tokenType,
            nameManager = this.nameManager,
            contactsManager = this.contactsManager
        )
    }
}
