package com.insta.task.api

import com.insta.task.model.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = " https://api.picsart.com"

class ApiClient {

    companion object {

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val retrofitService: LoginService by lazy {
            retrofit.create(LoginService::class.java)
        }

        val retrofitServiceUser: PhotoService by lazy {
            retrofit.create(PhotoService::class.java)
        }
    }
}