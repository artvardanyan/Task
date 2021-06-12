package com.insta.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.insta.task.databinding.ActivityMainBinding
import com.insta.task.fragments.LoginFragment

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