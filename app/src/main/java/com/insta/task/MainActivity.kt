package com.insta.task

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import com.insta.task.databinding.ActivityMainBinding
import com.insta.task.fragments.LoginFragment
import com.insta.task.fragments.UserFragment
import com.insta.task.model.LoginResponse

const val ERROR = "error"
const val SUCCESS = "success"
const val PREFERENCES_NAME = "preferences"
const val KEY = "is_logged"
const val USER_NAME = "user_name"
const val USER_ID = "user_id"
const val USER_PHOTO = "user_photo"
const val USER_DATA = "user_data"

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        supportFragmentManager.beginTransaction()
            .add(R.id.container, LoginFragment())
            .commit()

    }
}