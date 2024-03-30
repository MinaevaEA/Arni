package com.arni.remote.model.response

import com.arni.domain.model.ExecutorDomain
import com.google.gson.annotations.SerializedName

data class ExecutorResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)
fun ExecutorResponse.toDomain() = ExecutorDomain(
    guid = guid ?: "",
    name = name ?: ""
)
fun List<ExecutorResponse>.toDomain() = map { it.toDomain() }


