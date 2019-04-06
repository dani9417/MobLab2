package com.example.todoapp.ui.todo

import com.example.todoapp.model.Todo

interface TodoScreen {
    fun showTodos(todos: List<Todo>?)
    fun showNetworkError(error: String)
}