package com.example.todoapp.mock


import com.example.todoapp.network.TodoApi
import com.example.todoapp.network.UserApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class MockNetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideTodoApi(): TodoApi = MockTodoApi()

    @Provides
    @Singleton
    fun provideUserApi(): UserApi = MockUserApi()

}
