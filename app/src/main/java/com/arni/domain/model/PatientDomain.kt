package com.arni.domain.model

import com.arni.presentation.model.human.PatientHuman


data class PatientDomain(
    val name: String
)
fun PatientDomain.toHuman() = PatientHuman(
    name = name
)

fun List<PatientDomain>.toHuman() = map { it.toHuman() }
