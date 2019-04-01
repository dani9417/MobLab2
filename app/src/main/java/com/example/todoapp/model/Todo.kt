package com.example.todoapp.model

import com.google.gson.annotations.SerializedName


data class Todo(
    @SerializedName("id")
    var id: Int,
    @SerializedName("userid")
    var userId: Int,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("completed")
    var completed: Boolean
)