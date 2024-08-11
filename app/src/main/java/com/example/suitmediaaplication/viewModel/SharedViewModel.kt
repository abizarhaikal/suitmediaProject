package com.example.suitmediaaplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val name = MutableLiveData<String>()
}