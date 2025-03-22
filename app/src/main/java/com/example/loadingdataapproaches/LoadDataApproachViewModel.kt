package com.example.loadingdataapproaches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoadDataApproachViewModel: ViewModel() {

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.Companion.WhileSubscribed(5000L),
            false
        )

    // Second Approach
//    init { loadData() }


    fun loadData() {
        println("loading Data")
        viewModelScope.launch {
            _loadingState.update { true }
            delay(3000)
            _loadingState.update { false }
        }

    }
}