package com.example.todoapp.interactor.users.event

import com.example.todoapp.model.User

data class GetSimpleUserEvent (
    var code: Int = 0,
    var user: User? = null,
    var throwable: Throwable? = null
)