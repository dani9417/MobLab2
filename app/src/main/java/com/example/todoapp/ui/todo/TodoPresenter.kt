package com.example.todoapp.ui.todo

import com.example.todoapp.model.Todo
import com.example.todoapp.ui.Presenter

class TodoPresenter: Presenter<TodoScreen>() {

    override fun attachScreen(screen: TodoScreen) {
        super.attachScreen(screen)
        // register events
    }

    override fun detachScreen() {
        // unregister events
        super.detachScreen()
    }

    fun refreshTodos() {

    }

    fun refreshUsers() {

    }

    fun updateTodo(todoId: Number, todo: Todo) {

    }

    fun deleteTodo(todoId: Number) {

    }
}