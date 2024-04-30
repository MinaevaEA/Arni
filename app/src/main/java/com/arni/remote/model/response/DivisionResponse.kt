package com.arni.remote.model.response

import com.arni.domain.model.DivisionDomain
import com.arni.domain.model.DivisionRequestDomain
import com.google.gson.annotations.SerializedName

//TODO модель только для заявок
data class DivisionResponse(
    @SerializedName("guid")
    val guid: String?,
    val name: String?,
    val role: String?,
    @SerializedName("executorcollection")
    val executors: List<ExecutorResponse>?,
    @SerializedName("departmentcollection")
    val department: List<DepartamentResponse>?,
)
fun DivisionResponse.toDomain() = DivisionDomain(
    guid = guid ?: "",
    name = name ?: "",
    role = role ?: "",
    executors = executors?.toDomain(),
    department = department?.toDomain()
)

fun List<DivisionResponse>.toDomain() = map { it.toDomain() }
