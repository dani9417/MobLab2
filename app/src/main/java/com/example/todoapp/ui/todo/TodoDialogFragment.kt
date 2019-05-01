package com.example.todoapp.ui.todo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.CheckBox
import android.widget.EditText
import com.example.todoapp.R
import com.example.todoapp.model.Todo
import kotlinx.android.synthetic.main.modify_todo_dialog.view.*

class TodoDialogFragment: DialogFragment() {

    interface ModifyTodoDialogListener {
        fun onModifyTodo(todo: Todo)
    }

    lateinit var etTodoTask: EditText
    lateinit var cbTodoCompleted: CheckBox



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

       var builder = AlertDialog.Builder(activity)
        builder.setTitle("Modify todo")

        builder.setPositiveButton("Save") { dialog, which -> modifyTodo() }

        builder.setNegativeButton("Cancel") {dialog, which -> dismiss() }


        var view = activity?.layoutInflater?.inflate(R.layout.modify_todo_dialog, null)

        etTodoTask = view!!.etTodoTask
        etTodoTask?.setText(arguments?.getString("name"))
        cbTodoCompleted = view.cbTodoCompleted
        cbTodoCompleted.isChecked = arguments!!.getBoolean("completed")

        builder.setView(view)

        return builder.create()
    }

    private fun modifyTodo() {
        var listener: ModifyTodoDialogListener = activity as ModifyTodoDialogListener
        val todo = Todo(
            id=arguments!!.getInt("id")!!,
            completed = cbTodoCompleted.isChecked,
            title = etTodoTask.text.toString(),
            userId= arguments!!.getInt("userId"))
        listener.onModifyTodo(todo)
        dismiss()
    }



    companion object {

        fun newInstance(todo: Todo): TodoDialogFragment {
            val bundle = Bundle()
            bundle.putString("name", todo.title)
            bundle.putBoolean("completed", todo.completed)
            bundle.putInt("id", todo.id)
            bundle.putInt("userId", todo.userId)
            val frag = TodoDialogFragment()
            frag.arguments = bundle
            return frag
        }
    }
}