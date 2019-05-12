package com.example.todoapp.ui.todo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.injector
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoUpdate
import kotlinx.android.synthetic.main.fragment_todo.*
import javax.inject.Inject

abstract class TodoFragment: Fragment(), TodoScreen, TodoDialogFragment.ModifyTodoDialogListener {


    private var todoAdapter:TodoAdapter? = null
    private val displayedTodos: MutableList<Todo> = mutableListOf()

    @Inject
    lateinit var todoPresenter: TodoPresenter


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
        todoPresenter.attachScreen(this)
        todoPresenter.refreshTodos()
    }

    override fun onDetach() {
        todoPresenter.detachScreen()
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()
        todoPresenter.refreshTodos()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewTodos.layoutManager = llm

        todoAdapter = TodoAdapter(context!!, displayedTodos )
        recyclerViewTodos.adapter = todoAdapter

        swipeRefreshLayoutTodos.setOnRefreshListener {
            todoPresenter.refreshTodos()
        }
    }

    override fun showTodos(todos: List<Todo>?) {
        swipeRefreshLayoutTodos.isRefreshing = false
        displayedTodos.clear()
        if(todos != null) {
            displayedTodos.addAll(todos)

        }
        todoAdapter?.notifyDataSetChanged()
    }

    override fun onModifyTodo(todo: Todo) {
      Log.d("modify", todo.toString())
    }

    override fun onCreateTodo(todo: TodoUpdate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun showNetworkError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}