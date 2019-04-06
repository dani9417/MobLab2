package com.example.todoapp.ui.todo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.todoapp.R

class TodoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_todos)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, TodoFragment.newInstance()).commit()
    }


}
