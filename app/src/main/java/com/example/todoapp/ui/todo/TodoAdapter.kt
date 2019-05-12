package com.example.todoapp.ui.todo

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.todoapp.R
import com.example.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_row.view.*


class TodoAdapter constructor(
    private val context: Context,
    private var todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    var deleteTodo : Todo? = null
    var deletePosition: Int? = null

    interface TodoAdapterListener {
        fun deleteTodo(todo: Todo?)
    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(com.example.todoapp.R.layout.todo_item_row, p0,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return todos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.todoCheck.isChecked = todo.completed
        holder.todoName.text = "Todo #${todo.id}"
        holder.todoTask.text = todo.title

        holder.todoName.setOnClickListener {
            val todoDialogFragment = TodoDialogFragment.newInstance(todo)
            val fm = (context as FragmentActivity).supportFragmentManager
            todoDialogFragment.show(fm, "TODO_DIALOG")
        }
    }

    fun deleteItem(position: Int) {
        deleteTodo = todos[position]
        deletePosition = position
        todos.removeAt(position)
        notifyItemRemoved(position)
        showUndoSnackbar()


    }

     fun showUndoSnackbar() {
        val view = (context as TodoActivity).findViewById<View>(R.id.todoContainer)
        val snackbar = Snackbar.make(
            view, "Undo delete",
            Snackbar.LENGTH_SHORT
        )
        snackbar.setAction("Undo") { undoDelete() }
         snackbar.addCallback(object:Snackbar.Callback() {
             override fun onDismissed(snackbar:Snackbar, event:Int) {
                 if(event != 1) {
                     (context as TodoAdapterListener).deleteTodo(deleteTodo)
                 }

             }
         })
         snackbar.show()
    }

    fun setTodos(todoList: MutableList<Todo>) {
        todos = todoList
        notifyDataSetChanged()
    }

    fun undoDelete() {
        todos.add(deletePosition!!, deleteTodo!!)
        notifyItemInserted(deletePosition!!)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var todoName: TextView = view.todoName
        var todoTask: TextView = view.todoTask
        var todoCheck: CheckBox = view.todoCheck
    }
}