package com.example.todoapp.interactor

import com.example.todoapp.interactor.todos.TodoInteractor
import com.example.todoapp.interactor.users.UserInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideTodoInteractor() = TodoInteractor()

    @Provides
    @Singleton
    fun provideUserInteractor() = UserInteractor()


}
