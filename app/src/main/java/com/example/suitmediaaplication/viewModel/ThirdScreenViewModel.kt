package com.example.suitmediaaplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediaaplication.data.DataItem
import com.example.suitmediaaplication.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class ThirdScreenViewModel(private val repository: UserRepository) : ViewModel() {

    val userPagingFlow: Flow<PagingData<DataItem>> = repository.getPagingData()
        .cachedIn(viewModelScope)
}
