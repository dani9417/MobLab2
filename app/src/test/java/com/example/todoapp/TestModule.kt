package com.example.todoapp

import android.content.Context
import com.example.todoapp.interactor.todos.TodoInteractor
import com.example.todoapp.interactor.users.UserInteractor
import com.example.todoapp.ui.main.MainPresenter
import com.example.todoapp.ui.todo.TodoPresenter
import dagger.Module
import dagger.Provides
import com.example.todoapp.utils.UiExecutor
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class TestModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideMainPresenter(userInteractor: UserInteractor, executor: Executor) = MainPresenter(executor,userInteractor)

    @Provides
    @Singleton
    fun provideTodoPresenter(executor: Executor, todoInteractor: TodoInteractor) = TodoPresenter(executor, todoInteractor)

    @Provides
    @Singleton
    fun provideNetworkExecutor(): Executor {
        return UiExecutor()
    }
}
