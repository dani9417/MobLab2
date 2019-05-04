package com.example.todoapp.ui.todo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.injector
import com.example.todoapp.model.DAO.TodoDatabase
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoUpdate
import com.example.todoapp.ui.SwipeToDeleteCallback
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

        val dbThread = Thread {
            val todos = TodoDatabase.getInstance(this).todoDao().getTodos()
            Log.d("todo_dao", todos.toString())
        }
        dbThread.start()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_todo,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == R.id.create_todo) {
            val todoDialogFragment = TodoDialogFragment.newInstance(null)
            todoDialogFragment.show(supportFragmentManager, "CREATE_TODO")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onModifyTodo(todo: Todo) {
        todoPresenter.updateTodo(todo.id, todo)
        val dbThread = Thread {
            TodoDatabase.getInstance(this@TodoActivity).todoDao().updateTodo(todo)

            runOnUiThread {
                todoPresenter.refreshTodos()
                todoAdapter?.notifyDataSetChanged()
            }
        }
        dbThread.start()
    }

    override fun onCreateTodo(todo: TodoUpdate) {
        todoPresenter.createTodo(todo)
        val dbThread = Thread {
            TodoDatabase.getInstance(this@TodoActivity)
                .todoDao()
                .createTodo(Todo(id=0,title = todo.title, userId = todo.userId, completed = todo.completed))

            runOnUiThread {
                todoPresenter.refreshTodos()
                todoAdapter?.notifyDataSetChanged()
            }
        }
        dbThread.start()

    }


    override fun showTodos(todos: List<Todo>?) {

        val dbThread = Thread {
            val todoDao = TodoDatabase.getInstance(this@TodoActivity).todoDao()
            val todosFromDb = todoDao.getTodos()

            swipeRefreshLayoutTodos.isRefreshing = false

            displayedTodos.clear()
            if(todosFromDb.isEmpty()) {
                if (todos != null) {
                    todos.forEach { todoDao.createTodo(it) }
                    displayedTodos.addAll(todos.sortedBy { -it.id })
                }
            }
            else {
                displayedTodos.addAll(todosFromDb.sortedBy { -it.id })
            }

            runOnUiThread {
                todoAdapter?.notifyDataSetChanged()
            }

        }


        dbThread.start()
    }

    override fun deleteTodo(todo: Todo?) {
        if (todo != null) {
            todoPresenter.deleteTodo(todo.id)
            val dbThread = Thread {
                TodoDatabase.getInstance(this@TodoActivity).todoDao().deleteTodo(todo)
                runOnUiThread {
                    todoPresenter.refreshTodos()
                    todoAdapter?.notifyDataSetChanged()
                }
            }
            dbThread.start()

        }

    }

    override fun showNetworkError(error: String) {
        Toast.makeText(this,error, Toast.LENGTH_LONG).show()
    }



}
