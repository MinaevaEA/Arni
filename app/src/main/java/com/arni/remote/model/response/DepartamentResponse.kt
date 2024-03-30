package com.arni.remote.model.response

import com.arni.domain.model.DepartamentDomain
import com.google.gson.annotations.SerializedName

data class DepartamentResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)


fun DepartamentResponse.toDomain() = DepartamentDomain(
    guid = guid ?: "",
    name = name ?: ""
)

fun List<DepartamentResponse>.toDomain() = map { it.toDomain() }
