package com.example.todoapp.interactor.users.event

import com.example.todoapp.model.User

data class GetUsersEvent (
    var code: Int = 0,
    var users: List<User>? = null,
    var throwable: Throwable? = null
)