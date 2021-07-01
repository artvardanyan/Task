package com.insta.task.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.insta.task.R
import com.insta.task.databinding.FragmentLoginBinding
import com.insta.task.model.LoginRequest
import com.insta.task.model.LoginResponse
import com.insta.task.viewmodel.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val ERROR = "error"
const val SUCCESS = "success"
const val PREFERENCES_NAME = "preferences"
const val KEY = "is_logged"
const val USER_NAME = "user_name"
const val USER_ID = "user_id"
const val USER_PHOTO = "user_photo"
const val USER_DATA = "user_data"

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private var viewModel: LoginViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel?.liveData?.observe(viewLifecycleOwner, {
            when (it.status) {
                ERROR -> {
                    Toast.makeText(context, "Error User Name & Password", Toast.LENGTH_SHORT).show()
                }
                SUCCESS -> {
                    val sharedPreferences = context?.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                    sharedPreferences?.edit {
                        putBoolean(KEY, true)
                        putString(USER_NAME, it.name)
                        putLong(USER_ID, it.id)
                        putString(USER_PHOTO, it.photo)
                        commit()
                    }

                    val user = LoginResponse(SUCCESS, it.id, it.username, it.name, it.email, true, it.photo )
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.container, UserFragment.newInstance(user))
                        ?.commit()
                }
            }
        })

        binding?.loginBtn?.setOnClickListener {
            viewModel?.login(binding?.userNameLayout?.editText?.text.toString(), binding?.passwordLayout?.editText?.text.toString())
        }

        binding?.userNameLayout?.editText?.doAfterTextChanged {
            enableButton(binding?.userName?.text.toString(), binding?.password?.text.toString())
        }

        binding?.passwordLayout?.editText?.doAfterTextChanged {
            enableButton(binding?.userName?.text.toString(), binding?.password?.text.toString())
        }
    }

    private fun enableButton(userName: String, password: String) {
        binding?.loginBtn?.isEnabled = userName.isNotBlank() && password.isNotBlank()
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}