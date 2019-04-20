package com.example.todoapp.ui.todo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.injector
import com.example.todoapp.model.Todo
import javax.inject.Inject

class TodoFragment: Fragment(), TodoScreen {


    @Inject
    lateinit var todoPresenter: TodoPresenter


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
        todoPresenter.attachScreen(this)
        todoPresenter.deleteTodo(1)
    }

    override fun onDetach() {
        todoPresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun showTodos(todos: List<Todo>?) {
        Log.d("Todos", todos.toString())
    }

    override fun showNetworkError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        fun newInstance(): TodoFragment {
            return TodoFragment()
        }
    }

}