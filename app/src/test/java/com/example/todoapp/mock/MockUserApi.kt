package com.example.todoapp.mock

import com.example.todoapp.model.User
import com.example.todoapp.network.UserApi
import retrofit2.Call

class MockUserApi: UserApi {
    override fun getUsers(): Call<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserById(userId: Int?): Call<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}