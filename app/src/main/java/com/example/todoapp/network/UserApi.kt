package com.example.todoapp.network

import com.example.todoapp.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface UserApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{userId}")
    fun getUserById(
        @Path("userId") userId: Int?
    ): Call<User>
}