package com.insta.task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProviders
import com.insta.task.R
import com.insta.task.databinding.FragmentLoginBinding
import com.insta.task.viewmodel.LoginViewModel

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
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, UserFragment())
                ?.commit()
        })

        binding?.loginBtn?.setOnClickListener {
            viewModel?.login(binding?.userName?.text.toString(), binding?.password?.text.toString())
        }

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
        } else {
            binding?.loginBtn?.isEnabled = false
        }
    }

    companion object {

    }
}