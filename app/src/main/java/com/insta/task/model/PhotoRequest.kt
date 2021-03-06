package com.insta.task.model

import com.google.gson.annotations.SerializedName

data class PhotoRequest(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)