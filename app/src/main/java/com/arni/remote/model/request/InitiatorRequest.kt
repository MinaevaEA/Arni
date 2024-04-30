package com.arni.remote.model.request

import com.google.gson.annotations.SerializedName

data class InitiatorRequest(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)
