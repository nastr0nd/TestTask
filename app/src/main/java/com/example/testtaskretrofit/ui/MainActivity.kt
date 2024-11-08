package com.example.testtaskretrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskretrofit.R
import com.example.testtaskretrofit.utils.UserViewModelFactory
import com.example.testtaskretrofit.data.network.api.UserApi
import com.example.testtaskretrofit.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
   private var job: Job? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      val interceptor = HttpLoggingInterceptor()
      interceptor.level = HttpLoggingInterceptor.Level.BODY

      val client = OkHttpClient.Builder()
         .addInterceptor(interceptor)
         .build()

      val retrofit = Retrofit.Builder()
         .baseUrl("https://dummyjson.com")
         .client(client)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
      val userApi = retrofit.create(UserApi::class.java)

      val userRepository = UserRepository(userApi)
      val userViewModelFactory = UserViewModelFactory(userRepository)
      val userViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

      job = CoroutineScope(Dispatchers.Main).launch {
         val user = userViewModel.fetchUser()
         Log.d("User", "User: $user")
      }
   }
}