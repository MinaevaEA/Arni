package com.arni.domain.model

import com.arni.presentation.model.human.StatusPatientHuman

data class StatusPatientDomain(
    val guid: String,
    val name: String
)
fun StatusPatientDomain.toHuman() = StatusPatientHuman(
    guid = guid,
    name = name
)
fun List<StatusPatientDomain>.toHuman() = map { it.toHuman()}
