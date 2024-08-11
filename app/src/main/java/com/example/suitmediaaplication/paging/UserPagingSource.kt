package com.example.suitmediaaplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.suitmediaaplication.data.ApiService
import com.example.suitmediaaplication.data.DataItem

class UserPagingSource (private val apiService: ApiService) : PagingSource<Int, DataItem>(){
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        TODO("Not yet implemented")
    }

}