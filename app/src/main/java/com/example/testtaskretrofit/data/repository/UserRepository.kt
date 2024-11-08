package com.example.testtaskretrofit.data.repository

import com.example.testtaskretrofit.data.model.User
import com.example.testtaskretrofit.data.network.api.UserApi

class UserRepository(private val userApi: UserApi) {

   suspend fun getUser(): User? {
      val response = userApi.getUser()
      return if (response.isSuccessful) {
         response.body()
      } else {
         null
      }
   }
}