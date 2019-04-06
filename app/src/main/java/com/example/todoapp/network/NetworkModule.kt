package com.example.todoapp.network

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

//    @Provides
    @Singleton
    fun provideHttpClient() {
        return
    }

//    @Provides
    @Singleton
    fun provideTodoApi()  {

    }

//    @Provides
    @Singleton
    fun provideUserApi() {

    }
}