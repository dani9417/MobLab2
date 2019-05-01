package com.example.todoapp.ui.todo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import com.example.todoapp.R
import com.example.todoapp.injector
import com.example.todoapp.model.Todo
import com.example.todoapp.ui.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_todo.*
import javax.inject.Inject

class TodoActivity : AppCompatActivity(), TodoScreen, TodoDialogFragment.ModifyTodoDialogListener, TodoAdapter.TodoAdapterListener {



    @Inject
    lateinit var todoPresenter: TodoPresenter

    private var todoAdapter:TodoAdapter? = null
    private val displayedTodos: MutableList<Todo> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_todos)
        injector.inject(this)


        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewTodos.layoutManager = llm

        todoAdapter = TodoAdapter(this, displayedTodos )
        recyclerViewTodos.adapter = todoAdapter
        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(todoAdapter!!))
        itemTouchHelper.attachToRecyclerView(recyclerViewTodos)

        swipeRefreshLayoutTodos.setOnRefreshListener {
            todoPresenter.refreshTodos()
        }
//        supportFragmentManager.beginTransaction().replace(R.id.fragment, TodoFragment.newInstance()).commit()
    }

    override fun onStart() {
        super.onStart()
        todoPresenter.attachScreen(this)
        todoPresenter.refreshTodos()
    }

    override fun onResume() {
        super.onResume()
        todoPresenter.refreshTodos()
    }

    override fun onStop() {
        todoPresenter.detachScreen()
        super.onStop()
    }

    override fun onModifyTodo(todo: Todo) {
        Log.d("mod", todo.toString())
        todoPresenter.updateTodo(todo.id, todo)
    }


    override fun showTodos(todos: List<Todo>?) {
        swipeRefreshLayoutTodos.isRefreshing = false
        displayedTodos.clear()
        if(todos != null) {
            displayedTodos.addAll(todos)

        }
        todoAdapter?.notifyDataSetChanged()
    }

    override fun deleteTodo(id: Int) {
      todoPresenter.deleteTodo(id)
    }

    override fun showNetworkError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}
