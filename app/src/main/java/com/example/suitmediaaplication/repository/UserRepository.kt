package com.example.suitmediaaplication.repository

import androidx.paging.PagingData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import androidx.paging.PagingSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.ExperimentalPagingApi
import com.example.suitmediaaplication.data.ApiService
import com.example.suitmediaaplication.data.DataItem
import com.example.suitmediaaplication.data.database.UserDatabase
import com.example.suitmediaaplication.data.database.UserRemoteMediator
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userDatabase: UserDatabase,
    private val apiService: ApiService
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getPagingData(): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // Adjust page size if needed
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(userDatabase, apiService),
            pagingSourceFactory = {
                userDatabase.userDao().getAllUser()
            }
        ).flow
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDatabase: UserDatabase, apiService: ApiService): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository(userDatabase, apiService)
            }.also { instance = it }
        }
    }
}
