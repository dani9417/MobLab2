package com.example.todoapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.injector
import com.example.todoapp.model.User
import com.example.todoapp.ui.todo.TodoActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


class MainActivity : AppCompatActivity(), MainScreen {

    @Inject
    lateinit var mainPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.todoapp.R.layout.activity_main)
        injector.inject(this)
        setSupportActionBar(toolbar)
        Fabric.with(this, Crashlytics())

        todoLogo.setOnClickListener {
            mainPresenter.navigateToTodoList()
            throw Error("Crash")
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
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    override fun getUsers(users: MutableList<User>) {
        Log.d("mainact", users.toString())
    }




}
