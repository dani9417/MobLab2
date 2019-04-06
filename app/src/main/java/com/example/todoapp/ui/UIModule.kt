package com.example.todoapp.ui

import android.content.Context
import com.example.todoapp.ui.main.MainPresenter
import com.example.todoapp.ui.todo.TodoPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun mainPresenter() = MainPresenter()

    @Provides
    @Singleton
    fun todoPresenter() = TodoPresenter()
}