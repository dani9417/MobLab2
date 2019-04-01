package com.example.todoapp.model

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("user")
    var user: User? = null
)