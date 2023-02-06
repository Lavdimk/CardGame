package com.ict.cardgame.api


import com.ict.cardgame.model.Data
import com.ict.cardgame.model.Login
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPI {
    @GET("users")
    fun getAllUsers() : Call<Data>


    @POST("login")
    fun login(@Body login: Login): Call<Login>
}