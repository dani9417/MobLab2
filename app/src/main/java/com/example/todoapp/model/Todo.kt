package com.example.todoapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "userid")
    @SerializedName("userid")
    var userId: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "completed")
    @SerializedName("completed")
    var completed: Boolean
)