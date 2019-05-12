package com.example.todoapp.interactor.todos

import android.util.Log
import com.example.todoapp.interactor.todos.event.DeleteTodoEvent
import com.example.todoapp.interactor.todos.event.GetTodosEvent
import com.example.todoapp.interactor.todos.event.TodoEvent
import com.example.todoapp.model.TodoUpdate
import com.example.todoapp.network.TodoApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class TodoInteractor @Inject constructor(private var todosApi: TodoApi) {

    fun getTodos() {

        val event = GetTodosEvent()

        try {
            val todosCall = todosApi.getTodos()
            val response = todosCall.execute()
            Log.d("Reponse", response.body().toString())
            if(response.code() != 200) {
                throw Exception("Result code is not 200, something happened")
            }
            event.code = response.code()
            event.todos = response.body()
            EventBus.getDefault().post(event)
        }
        catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun deleteTodo(todoId: Int) {
        val event = DeleteTodoEvent()

        try {
            val deleteCall = todosApi.deleteTodo(todoId)
            val response = deleteCall.execute()
            Log.d("Reponse", response.headers().toString())
            EventBus.getDefault().post(event)
        }
        catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun updateTodo(todoId: Int, todo: TodoUpdate) {
        val event = TodoEvent()

        try {
            val updateCall = todosApi.updateTodo(todoId, todo)
            val response = updateCall.execute()
            Log.d("Reponse", response.body().toString())
        }
        catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun getTodoById(todoId: Int) {
        val event = TodoEvent()

        try {
            val todosCall = todosApi.getTodoById(todoId)
            val response = todosCall.execute()
            Log.d("Reponse", response.body().toString())
            if(response.code() != 200) {
                throw Exception("Result code is not 200, something happened")
            }
            event.code = response.code()
            event.todo = response.body()
            EventBus.getDefault().post(event)
        }
        catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun createNewTodo(todo: TodoUpdate) {
        val event = TodoEvent()

        try {
            val todosCall = todosApi.createTodo(todo)
            val response = todosCall.execute()
            Log.d("Reponse", response.body().toString())
            if(response.code() != 200) {
                throw Exception("Result code is not 200, something happened")
            }
            event.code = response.code()
            event.todo = response.body()
            EventBus.getDefault().post(event)
        }
        catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }
}