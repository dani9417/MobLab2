package com.example.todoapp.ui

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.todoapp.ui.todo.TodoAdapter

class SwipeToDeleteCallback constructor(
    private val todoAdapter: TodoAdapter
) : ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT) {



    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSwiped(holder: RecyclerView.ViewHolder, p1: Int) {
       val position = holder.adapterPosition
        todoAdapter.deleteItem(position)
    }
}