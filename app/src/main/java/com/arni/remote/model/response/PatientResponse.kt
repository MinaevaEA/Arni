package com.arni.remote.model.response

import com.arni.domain.model.PatientDomain

data class PatientResponse(
    val name: String?
)
fun PatientResponse.toDomain() = PatientDomain(
    name = name ?: ""
)

fun List<PatientResponse>.toDomain() = map { it.toDomain() }

