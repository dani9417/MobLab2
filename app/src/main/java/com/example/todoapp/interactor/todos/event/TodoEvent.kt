package com.example.todoapp.interactor.todos.event

import com.example.todoapp.model.Todo

data class TodoEvent (
    var code: Int = 0,
    var todo: Todo? = null,
    var throwable: Throwable? = null
)