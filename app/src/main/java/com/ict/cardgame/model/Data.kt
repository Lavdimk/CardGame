package com.ict.cardgame.model

data class Login(val email: String, val password: String)
data class Data(val data : List<User> )
data class User(

    val first_name: String,
    val last_name: String,
    val id: Int?,
    val avatar: String?
)
