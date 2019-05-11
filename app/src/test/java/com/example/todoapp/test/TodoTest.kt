package com.example.todoapp.test

import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoUpdate
import com.example.todoapp.testInjector
import com.example.todoapp.ui.todo.TodoDialogFragment
import com.example.todoapp.ui.todo.TodoPresenter
import com.example.todoapp.ui.todo.TodoScreen
import com.example.todoapp.utils.argumentCaptor
import com.example.todoapp.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class TodoTest {

    @Inject
    lateinit var todoPresenter: TodoPresenter
    private lateinit var todoScreen: TodoScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        todoScreen = mock()
        todoPresenter.attachScreen(todoScreen)
    }

    @Test
    fun getTodos() {
        val list = argumentCaptor<MutableList<Todo>>()
        todoPresenter.refreshTodos()
        verify(todoScreen).showTodos(list.capture())
        assert(list.value.size > 0)
        val todo = list.value[0]
        assert(todo.title == "Test")
        assert(todo.completed)
    }

    @Test
    fun createTodo() {
        val todoUpdate = TodoUpdate(1,"Test", false)
        todoPresenter.createTodo(todoUpdate)
        todoPresenter.refreshTodos()
        val list = argumentCaptor<MutableList<Todo>>()
        verify(todoScreen).showTodos(list.capture())
        val todo = list.value[0]
        assert(todo.title == "Test")



    }

    @After
    fun tearDown() {
        todoPresenter.detachScreen()
    }

}