package com.example.todoapp.ui.main

import com.example.todoapp.interactor.users.UserInteractor
import com.example.todoapp.interactor.users.event.GetUsersEvent
import com.example.todoapp.model.User
import com.example.todoapp.ui.Presenter
import com.example.todoapp.ui.todo.TodoScreen
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class MainPresenter @Inject constructor(private val executor: Executor, private val userInteractor: UserInteractor) : Presenter<MainScreen>() {

    override fun attachScreen(screen: MainScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun navigateToTodoList() {
        screen?.showTodos()
    }

    fun fetchUsers() {
        executor.execute {
            userInteractor.getUsers()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetUsersEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {

            }
        } else {
            if (screen != null) {
                if (event.users != null) {
                    screen?.getUsers(event.users as MutableList<User>)
                }

            }
        }
    }




}