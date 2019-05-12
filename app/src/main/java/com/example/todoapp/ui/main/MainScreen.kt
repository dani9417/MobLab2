package com.example.todoapp.ui.main

import com.example.todoapp.model.User

interface MainScreen {
    fun showTodos()

    fun getUsers(mutableList: MutableList<User>)
}