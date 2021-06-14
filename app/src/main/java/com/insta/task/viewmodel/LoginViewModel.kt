package com.insta.task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insta.task.api.ApiClient
import com.insta.task.model.LoginRequest
import com.insta.task.model.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    var liveData: MutableLiveData<LoginResponse> = MutableLiveData()

    fun login(name: String?, password: String?) {
        if (name == null || password == null) return

        val loginRequest = LoginRequest(name, password)
        val loginResponse: Call<LoginResponse?>? = ApiClient.retrofitService.userLogin(loginRequest)

        loginResponse?.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                viewModelScope.launch {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

}