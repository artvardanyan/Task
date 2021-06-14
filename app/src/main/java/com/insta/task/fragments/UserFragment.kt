package com.insta.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.insta.task.R
import com.insta.task.databinding.FragmentUserBinding
import com.insta.task.model.LoginResponse

class UserFragment : Fragment() {

    private var binding: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user: LoginResponse? = arguments?.getParcelable(USER_DATA)
        userPhoto(user)

        binding?.logoutButton?.setOnClickListener{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, LoginFragment())
                ?.commit()
        }
    }

    companion object {
        fun newInstance(user: LoginResponse): UserFragment {
            val args = Bundle()
            args.putParcelable(USER_DATA, user)

            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun userPhoto(user: LoginResponse?) {
        binding?.let {
            Glide.with(this)
                .load(user?.photo)
                .into(it.imageView)
            it.id.text = "id: ${user?.id.toString()}"
            it.username.text = "Name: ${user?.name}"
        }
    }

}