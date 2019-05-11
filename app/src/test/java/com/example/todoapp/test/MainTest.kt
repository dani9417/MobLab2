package com.example.todoapp.test

import com.example.todoapp.testInjector
import com.example.todoapp.ui.main.MainPresenter
import com.example.todoapp.ui.main.MainScreen
import com.example.todoapp.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject
import org.mockito.Mockito.verify
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
@RunWith(RobolectricTestRunner::class)
class MainTest {
    @Inject
    lateinit var mainPresenter: MainPresenter
    private lateinit var mainScreen: MainScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        mainScreen = mock()
        mainPresenter.attachScreen(mainScreen)
    }

    @Test
    fun navigationToTodos() {
        mainPresenter.navigateToTodoList()
        verify(mainScreen).showTodos()
    }

    @After
    fun tearDown() {
        mainPresenter.detachScreen()
    }
}