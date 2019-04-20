package com.example.todoapp.model.DAO

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.todoapp.model.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId: Int): User
}