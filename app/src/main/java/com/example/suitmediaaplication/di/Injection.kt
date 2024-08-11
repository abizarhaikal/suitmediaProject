package com.example.suitmediaaplication.di

import android.content.Context
import com.example.suitmediaaplication.data.ApiConfig
import com.example.suitmediaaplication.data.database.UserDatabase
import com.example.suitmediaaplication.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val userDatabase = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(userDatabase, apiService)
    }
}
