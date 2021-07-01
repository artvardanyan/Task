package com.insta.task.api

import androidx.lifecycle.MutableLiveData
import com.insta.task.model.PhotoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PhotoService {
    @POST("/stage/photos/freetoedit/search.json?q=&offset=0&limit=50")
    fun userLogin(@Body PhotoRequest: MutableLiveData<PhotoResponse>): Call<PhotoResponse?>?
}