package com.example.todoapp.ui.todo

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
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
    private var todos: List<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.todo_item_row, p0,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return todos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.todoCheck.isChecked = todo.completed
        holder.todoName.text = "Todo #${position+1}"
        holder.todoTask.text = todo.title

        holder.todoName.setOnClickListener {
            var todoDialogFragment = TodoDialogFragment.newInstance(todo)
            val fm = (context as FragmentActivity).supportFragmentManager
            todoDialogFragment.show(fm, "TODO_DIALOG")
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var todoName: TextView = view.todoName
        var todoTask: TextView = view.todoTask
        var todoCheck: CheckBox = view.todoCheck
    }
}