package com.arni.remote.model.response

import com.arni.domain.model.UrgencyDomain
import com.google.gson.annotations.SerializedName

data class UrgencyResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)
fun UrgencyResponse.toDomain() = UrgencyDomain(
    guid = guid ?: "",
    name = name ?: ""
)
fun List<UrgencyResponse>.toDomain() = map { it.toDomain() }
