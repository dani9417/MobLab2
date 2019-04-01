package com.example.todoapp

import android.app.Application

class TodoAppApplication: Application() {
    lateinit var injector: TodoAppApplicationComponent

    override fun onCreate() {
        super.onCreate()

    }
}