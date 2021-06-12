package com.insta.task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

     var liveData: MutableLiveData<String> = MutableLiveData()

    fun login(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            liveData.value = "dddd"
        }
    }

}