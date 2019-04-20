package com.example.todoapp.interactor.users

import android.util.Log
import com.example.todoapp.interactor.users.event.GetSimpleUserEvent
import com.example.todoapp.interactor.users.event.GetUsersEvent
import com.example.todoapp.network.UserApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class  UserInteractor @Inject constructor(private var userApi: UserApi) {

    fun getUsers() {
        val event = GetUsersEvent()

        try {
            val usersCall = userApi.getUsers()
            val response = usersCall.execute()
            Log.d("Reponse", response.body().toString())
            if(response.code() != 200) {
                throw Exception("Result code is not 200, something happened")
            }
            event.code = response.code()
            event.users = response.body()
            EventBus.getDefault().post(event)
        }
        catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun getUserById(userId: Int) {
        val event = GetSimpleUserEvent()

        try {
            val userCall = userApi.getUserById(userId)
            val response = userCall.execute()
            Log.d("Reponse", response.body().toString())
            if(response.code() != 200) {
                throw Exception("Result code is not 200, something happened")
            }
            event.code = response.code()
            event.user = response.body()
            EventBus.getDefault().post(event)
        }
        catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }
}