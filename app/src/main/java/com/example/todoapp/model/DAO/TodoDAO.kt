package com.example.todoapp.model.DAO

import android.arch.persistence.room.*
import com.example.todoapp.model.Todo

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo")
    fun getTodos(): List<Todo>

    @Query("SELECT * FROM todo WHERE id = :todoId")
    fun getTodoById(todoId: Int): Todo

    @Delete
    fun deleteTodo(todo: Todo)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createTodo(vararg todo: Todo)

    @Update
    fun updateTodo(todo: Todo)
}