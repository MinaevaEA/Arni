package com.arni.remote.model.response

import com.arni.domain.model.StatusItemDomain
import com.arni.domain.model.StatusPatientDomain
import com.google.gson.annotations.SerializedName

data class StatusPatientResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?
)
fun StatusPatientResponse.toDomain() = StatusPatientDomain(
    guid = guid ?: "",
    name = name ?: ""
)

fun List<StatusPatientResponse>.toDomain() = map { it.toDomain() }
