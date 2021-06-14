package com.insta.task.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("default_profile_pic")
    val default_profile_pic: Boolean,
    @SerializedName("photo")
    val photo: String
) : Parcelable