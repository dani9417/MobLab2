package com.example.todoapp

import android.app.Application
import com.example.todoapp.ui.UIModule

class TodoAppApplication: Application() {
    lateinit var injector: TodoAppApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerTodoAppApplicationComponent.builder().uIModule(UIModule(this)).build()


    }
}