package com.example.retrofitdemo.retrofit

import com.example.retrofitdemo.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getAllUser() : Response<List<User>>
}