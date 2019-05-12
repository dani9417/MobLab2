package com.example.todoapp.ui.todo

import com.example.todoapp.interactor.todos.TodoInteractor
import com.example.todoapp.interactor.todos.event.DeleteTodoEvent
import com.example.todoapp.interactor.todos.event.GetTodosEvent
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoUpdate
import com.example.todoapp.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class TodoPresenter @Inject constructor(private val executor: Executor, private val todosInteractor: TodoInteractor) : Presenter<TodoScreen>() {

    override fun attachScreen(screen: TodoScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)

    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun refreshTodos() {
        executor.execute {
            todosInteractor.getTodos()
        }

    }

    fun  createTodo(todo: TodoUpdate) {
        executor.execute {
            todosInteractor.createNewTodo(todo)
        }
    }


    fun updateTodo(todoId: Int, todo: Todo) {
        executor.execute {
            todosInteractor.updateTodo(todoId, TodoUpdate(todoId, todo.title, todo.completed))
        }
    }

    fun deleteTodo(todoId: Int) {
        executor.execute {
            todosInteractor.deleteTodo(todoId)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetTodosEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.todos != null) {
                    screen?.showTodos(event.todos as MutableList<Todo>)
                }

            }
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onDeleteEvent(event: DeleteTodoEvent) {
        if(event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        }
        else {
            if (screen != null) {

            }
        }
    }
}