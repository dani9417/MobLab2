package com.example.todoapp.network

import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoResult
import com.example.todoapp.model.TodoUpdate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.DELETE





interface TodoApi {
    @GET("todos")
    fun getTodos(): Call<TodoResult>

    @POST("todos")
    fun createTodo(@Body body: TodoUpdate): Call<Void>

    @GET("todos/{todoId}")
    fun getTodoById(
        @Path("todoId") todoId: Int?
    ): Call<Todo>

    @PUT("todos/{todoId}")
    fun updateTodo(
        @Path("todoId") todoId: Int?, @Body body: TodoUpdate
    ): Call<Void>

    @DELETE("todos/{todoId}")
    fun deleteTodo(
        @Path("todoId") todoId: Int?
    ): Call<Void>
}