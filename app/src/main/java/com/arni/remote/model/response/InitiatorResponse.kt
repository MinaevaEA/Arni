package com.arni.remote.model.response

import com.arni.domain.model.InitiatorDomain
import com.google.gson.annotations.SerializedName

data class InitiatorResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)
fun InitiatorResponse.toDomain() = InitiatorDomain(
    guid = guid ?: "",
    name = name ?: ""
)
