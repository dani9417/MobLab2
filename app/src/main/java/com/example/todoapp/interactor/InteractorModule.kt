package com.example.todoapp.interactor

import com.example.todoapp.interactor.todos.TodoInteractor
import com.example.todoapp.interactor.users.UserInteractor
import com.example.todoapp.network.TodoApi
import com.example.todoapp.network.UserApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideTodoInteractor(todoApi: TodoApi) = TodoInteractor(todoApi)

    @Provides
    @Singleton
    fun provideUserInteractor(userApi: UserApi) = UserInteractor(userApi)


}
