package com.example.todoapp.ui.main

import com.example.todoapp.ui.Presenter

class MainPresenter: Presenter<MainScreen>() {

    fun navigateToTodoList() {
        screen?.showTodos()
    }

}