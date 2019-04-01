package com.example.todoapp.model

import com.google.gson.annotations.SerializedName

data class TodoResult(
    @SerializedName("todo")
    var todo: Todo? = null
)