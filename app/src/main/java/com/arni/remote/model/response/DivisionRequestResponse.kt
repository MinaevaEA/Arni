package com.arni.remote.model.response

import com.arni.domain.model.DivisionRequestDomain
import com.google.gson.annotations.SerializedName

//TODO модель только для заявок
data class DivisionRequestResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?,
)
fun DivisionRequestResponse.toDomain() = DivisionRequestDomain(
    guid = guid ?: "",
    name = name ?: "",
)

fun List<DivisionRequestResponse>.toDomain() = map { it.toDomain() }
