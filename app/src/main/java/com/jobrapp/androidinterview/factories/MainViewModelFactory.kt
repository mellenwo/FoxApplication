package com.jobrapp.androidinterview.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jobrapp.androidinterview.UserViewModel
import com.jobrapp.androidinterview.data.UserRepository

class MainViewModelFactory(private val repository: UserRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}