package com.insta.task.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.insta.task.R
import com.insta.task.adapter.PhotoAdapter
import com.insta.task.databinding.FragmentUserBinding
import com.insta.task.model.LoginResponse
import com.insta.task.model.PhotoRequest
import com.insta.task.model.PhotoResponse
import com.insta.task.viewmodel.UserViewModel
import java.util.*

class UserFragment : Fragment() {

    private var binding: FragmentUserBinding? = null
    private var userViewModel: UserViewModel? = null
    private var adapter: PhotoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user: LoginResponse? = arguments?.getParcelable(USER_DATA)
        userPhoto(user)

        binding?.logoutButton?.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, LoginFragment())
                ?.commit()
        }

        binding?.recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = PhotoAdapter(getTitle)
        }

        userViewModel?.liveData?.observe(viewLifecycleOwner, {

        })

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchTest = newText!!.toLowerCase(Locale.getDefault())
                if (searchTest.isNotBlank()) {
                    getTitle.forEach {
                        if (it.title.toLowerCase(Locale.getDefault()).contains(searchTest)) {
                            getTitle.add(it)
                        }
                    }
                    adapter?.notifyDataSetChanged()
                }
                return false
            }
        })

    }

    private val getTitle = mutableListOf(
        PhotoRequest(1987, "Aaising Arizona", ""),
        PhotoRequest(2000, "Baising Arizona", ""),
        PhotoRequest(2001, "Caising Arizona", ""),
        PhotoRequest(1989, "Raising Arizona", ""),
        PhotoRequest(1965, "Raising Arizona", ""),
        PhotoRequest(1994, "Raising Arizona", ""),
        PhotoRequest(2005, "Raising Arizona", ""),
        PhotoRequest(2012, "Raising Arizona", ""),
        PhotoRequest(1986, "Raising Arizona", ""),
        PhotoRequest(1998, "Raising Arizona", ""),
    )


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