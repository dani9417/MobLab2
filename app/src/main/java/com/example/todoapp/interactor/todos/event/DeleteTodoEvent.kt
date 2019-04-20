package com.example.todoapp.interactor.todos.event

import com.example.todoapp.model.Todo

data class DeleteTodoEvent(
    var code: Int = 0,
    var throwable: Throwable? = null
)