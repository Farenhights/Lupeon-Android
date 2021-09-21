package br.com.henriktech.lupeon.data.model

import br.com.henriktech.lupeon.api.model.Login
import br.com.henriktech.lupeon.database.db.UserEntity

data class User(
    private val userId: Int,
    private val accessToken: String,
    private val companyId: String,
    private val email: String,
    private val expiresIn: Int,
    private val login: String,
    private val name: String,
    private val userType: String,
    private val tokenType: String,
    private val nameManager: String,
    private val contactsManager: String
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
