package com.insta.task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insta.task.api.ApiClient
import com.insta.task.model.PhotoRequest
import com.insta.task.model.PhotoResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    var liveData: MutableLiveData<PhotoResponse> = MutableLiveData()

    val photoResponse: Call<PhotoResponse?>? = ApiClient.retrofitServiceUser.userLogin(liveData)

    fun user() {
        photoResponse?.enqueue(object : Callback<PhotoResponse?> {
            override fun onResponse(call: Call<PhotoResponse?>, response: Response<PhotoResponse?>) {
                viewModelScope.launch {
                    liveData.value = response.body()

                }
            }

            override fun onFailure(call: Call<PhotoResponse?>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}