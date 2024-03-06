package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class NavigationViewModel : ViewModel() {

    var navState = mutableStateOf(NavigationState())
        private set


    fun onChange(index: Int) {
        navState.value = navState.value.copy(
            selectedItem = index
        )
    }

}