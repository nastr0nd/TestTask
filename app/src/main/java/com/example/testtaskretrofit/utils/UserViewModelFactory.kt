package com.example.testtaskretrofit.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskretrofit.data.repository.UserRepository
import com.example.testtaskretrofit.ui.UserViewModel

class UserViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
   override fun <T : ViewModel> create(modelClass:Class<T>): T {
      if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
         return UserViewModel(userRepository) as T
      }
      throw IllegalArgumentException("Unknown ViewModel class")
   }
}