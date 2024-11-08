package com.example.testtaskretrofit.data.network.api

import com.example.testtaskretrofit.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
   @GET("users/1")
   suspend fun getUser(): Response<User>
}