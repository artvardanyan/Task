package com.insta.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.insta.task.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.userNameLayout?.editText?.doAfterTextChanged {
            enableButton(binding?.userName?.text.toString(), binding?.password?.text.toString())
        }

        binding?.passwordLayout?.editText?.doAfterTextChanged {
            enableButton(binding?.userName?.text.toString(), binding?.password?.text.toString())
        }
    }

    private fun enableButton(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            binding?.loginBtn?.isEnabled = true
        }
    }

    companion object {

    }
}