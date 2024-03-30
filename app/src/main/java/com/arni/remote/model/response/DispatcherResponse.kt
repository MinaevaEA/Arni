package com.arni.remote.model.response

import com.arni.domain.model.DispatcherDomain
import com.google.gson.annotations.SerializedName

data class DispatcherResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)
fun DispatcherResponse.toDomain() = DispatcherDomain(
    guid = guid ?: "",
    name = name ?: ""
)
