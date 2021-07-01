package com.insta.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.recyclerview.widget.RecyclerView
import com.insta.task.R
import com.insta.task.databinding.PhotoItemBinding
import com.insta.task.model.PhotoRequest

class PhotoAdapter(private var photoRequest: List<PhotoRequest>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var mTitleView: TextView? = null
        private var mIdView: TextView? = null
        private var mImageView: ImageView? = null

        init {
            mTitleView = itemView.findViewById(R.id.photoTitle)
            mIdView = itemView.findViewById(R.id.photoId)
            mImageView = itemView.findViewById(R.id.imageView)
        }

        fun bind(photoRequest: PhotoRequest) {
            mTitleView?.text = photoRequest.title
            mIdView?.text = photoRequest.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhotoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return photoRequest.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo: PhotoRequest = photoRequest[position]
        holder.bind(photo)

    }

}