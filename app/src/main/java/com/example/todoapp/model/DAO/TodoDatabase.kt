package com.example.todoapp.model.DAO

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.todoapp.model.Todo

@Database(entities = [Todo::class], version = 2)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDAO

    companion object {
        private var INSTANCE: TodoDatabase? = null
        fun getInstance(context: Context): TodoDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    TodoDatabase::class.java, "todo.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}