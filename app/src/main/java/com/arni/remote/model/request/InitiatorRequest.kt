package com.arni.remote.model.request

import com.google.gson.annotations.SerializedName

data class InitiatorRequest(
    @SerializedName("guid")
    val guid: String? = "00000000-0000-0000-0000-000000000000",
    val name: String?
)
