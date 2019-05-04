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
import com.example.todoapp.model.TodoUpdate
import kotlinx.android.synthetic.main.modify_todo_dialog.view.*

class TodoDialogFragment: DialogFragment() {

    interface ModifyTodoDialogListener {
        fun onModifyTodo(todo: Todo)
        fun onCreateTodo(todo: TodoUpdate)
    }

    lateinit var etTodoTask: EditText
    lateinit var cbTodoCompleted: CheckBox



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val createTodo = arguments?.getBoolean("createTodo")
        val builder = AlertDialog.Builder(activity)
        val title = if (createTodo == true)  "Create todo" else "Modify todo"
        builder.setTitle(title)

        builder.setPositiveButton("Save") { dialog, which ->
            run {
                if (createTodo == true) createTodo() else modifyTodo()
            }
        }

        builder.setNegativeButton("Cancel") {dialog, which -> dismiss() }


        val view = activity?.layoutInflater?.inflate(R.layout.modify_todo_dialog, null)

        etTodoTask = view!!.etTodoTask
        cbTodoCompleted = view.cbTodoCompleted

        if(createTodo != true) {
            etTodoTask.setText(arguments?.getString("name"))
            cbTodoCompleted.isChecked = arguments!!.getBoolean("completed")
        }


        builder.setView(view)

        return builder.create()
    }

    private fun createTodo() {
        val listener: ModifyTodoDialogListener = activity as ModifyTodoDialogListener
        val todo = TodoUpdate(
            completed = cbTodoCompleted.isChecked,
            title = etTodoTask.text.toString(),
            userId= 1
        )
        listener.onCreateTodo(todo)
        dismiss()
    }

    private fun modifyTodo() {
        val listener: ModifyTodoDialogListener = activity as ModifyTodoDialogListener
        val todo = Todo(
            id=arguments!!.getInt("id"),
            completed = cbTodoCompleted.isChecked,
            title = etTodoTask.text.toString(),
            userId= arguments!!.getInt("userId"))
        listener.onModifyTodo(todo)
        dismiss()
    }



    companion object {

        fun newInstance(todo: Todo?): TodoDialogFragment {
            val frag = TodoDialogFragment()
            val bundle = Bundle()

            if(todo != null) {
                bundle.putString("name", todo.title)
                bundle.putBoolean("completed", todo.completed)
                bundle.putInt("id", todo.id)
                bundle.putInt("userId", todo.userId)
                bundle.putBoolean("createTodo", false)

            }
            else {
                bundle.putBoolean("createTodo", true)
            }
            frag.arguments = bundle

            return frag
        }
    }
}