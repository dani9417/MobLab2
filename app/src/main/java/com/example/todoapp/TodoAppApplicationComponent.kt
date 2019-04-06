package com.example.todoapp

import com.example.todoapp.interactor.InteractorModule
import com.example.todoapp.network.NetworkModule
import com.example.todoapp.ui.UIModule
import com.example.todoapp.ui.main.MainActivity
import com.example.todoapp.ui.todo.TodoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, InteractorModule::class, NetworkModule::class])
interface TodoAppApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(todoFragment: TodoFragment)
}