package com.example.todoapp.mock

import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoUpdate
import com.example.todoapp.network.TodoApi
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MockTodoApi : TodoApi {
    override fun createTodo(body: TodoUpdate): Call<Todo> {
        val newTodo = Todo(999,body.userId,body.title,body.completed)
        val call = object : Call<Todo> {
            override fun enqueue(callback: Callback<Todo>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isExecuted(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun clone(): Call<Todo> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isCanceled(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun cancel() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun execute(): Response<Todo> {
                return Response.success(newTodo)
            }

            override fun request(): Request {
                TODO("not implemented")
            }

        }
        return call
    }

    override fun getTodoById(todoId: Int?): Call<Todo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTodo(todoId: Int?, body: TodoUpdate): Call<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTodo(todoId: Int?): Call<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTodos(): Call<List<Todo>> {
        val todo = Todo(1,1,"Test",true)
        val todos =  ArrayList<Todo>()
        todos.add(todo)
        val call = object : Call<List<Todo>> {
            override fun enqueue(callback: Callback<List<Todo>>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun clone(): Call<List<Todo>> {
                return this
            }

            override fun isCanceled(): Boolean {
               return false
            }

            override fun cancel() {

            }

            override fun execute(): Response<List<Todo>> {
                return Response.success(todos)
            }

            override fun request(): Request? {
               return null
            }

        }
        return call
    }


}
