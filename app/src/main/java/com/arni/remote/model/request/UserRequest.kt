package com.arni.remote.model.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("username")
    val userName: String,
    val password: String
)
