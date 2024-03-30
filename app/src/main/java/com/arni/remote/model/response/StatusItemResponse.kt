package com.arni.remote.model.response

import com.arni.domain.model.StatusItemDomain
import com.google.gson.annotations.SerializedName

data class StatusItemResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)

fun StatusItemResponse.toDomain() = StatusItemDomain(
    guid = guid ?: "",
    name = name ?: ""
)

fun List<StatusItemResponse>.toDomain() = map { it.toDomain() }
