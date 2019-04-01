package com.example.todoapp

import com.example.todoapp.ui.todo.TodoFragment
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface TodoAppApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(todoFragment: TodoFragment)
}