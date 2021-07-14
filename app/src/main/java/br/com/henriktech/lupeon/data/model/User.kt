package br.com.henriktech.lupeon.data.model

data class User(
    var name: String,
    var token: String,
    var refreshToken: String
) {
    fun getFirstName() : String {
        val split = name.split(" ")
        return split[0]
    }
}
