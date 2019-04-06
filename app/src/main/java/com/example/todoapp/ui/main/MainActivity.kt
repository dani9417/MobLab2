package com.example.todoapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.injector
import com.example.todoapp.ui.todo.TodoActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen {

    @Inject
    lateinit var mainPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injector.inject(this)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            mainPresenter.navigateToTodoList()
        }

    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }

    override fun showTodos() {
        Toast.makeText(this, "navigate", Toast.LENGTH_LONG).show()
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }



}
