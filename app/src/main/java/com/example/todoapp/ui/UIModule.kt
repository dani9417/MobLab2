package com.example.todoapp.ui

import android.content.Context
import com.example.todoapp.interactor.todos.TodoInteractor
import com.example.todoapp.ui.main.MainPresenter
import com.example.todoapp.ui.todo.TodoPresenter
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
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
    fun todoPresenter(executor: Executor, todoInteractor: TodoInteractor) = TodoPresenter(executor,todoInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}