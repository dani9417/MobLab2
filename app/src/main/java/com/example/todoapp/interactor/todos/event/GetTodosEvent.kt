package com.example.todoapp.interactor.todos.event

import com.example.todoapp.model.Todo

data class GetTodosEvent (
    var code: Int = 0,
    var todos: List<Todo>? = null,
    var throwable: Throwable? = null
)