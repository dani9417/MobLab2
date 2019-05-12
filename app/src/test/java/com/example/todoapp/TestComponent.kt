package com.example.todoapp

import com.example.todoapp.interactor.InteractorModule
import com.example.todoapp.mock.MockNetworkModule
import dagger.Component
import com.example.todoapp.test.MainTest
import com.example.todoapp.test.TodoTest
import javax.inject.Singleton

@Singleton
@Component(modules = [ TestModule::class, InteractorModule::class, MockNetworkModule::class])
interface TestComponent : TodoAppApplicationComponent {
    fun inject(mainTest: MainTest)
    fun inject(todoTest: TodoTest)
}
