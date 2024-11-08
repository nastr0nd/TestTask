package com.example.testtaskretrofit.ui

import androidx.lifecycle.ViewModel
import com.example.testtaskretrofit.data.model.User
import com.example.testtaskretrofit.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

   suspend fun fetchUser(): User? {
      return withContext(Dispatchers.IO) {
         userRepository.getUser()
      }
   }
}