package com.arni.remote.model.request

import com.google.gson.annotations.SerializedName

data class StatusItemRequest(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)
