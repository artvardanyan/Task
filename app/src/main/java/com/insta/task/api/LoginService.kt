package com.insta.task.api

import com.insta.task.model.LoginRequest
import com.insta.task.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginService {
    @POST("/users/signin.json")
    fun userLogin(@Body LoginRequest: LoginRequest?): Call<LoginResponse?>?
}