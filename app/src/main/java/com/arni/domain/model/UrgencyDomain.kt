package com.arni.domain.model

import com.arni.presentation.model.human.UrgencyHuman

data class UrgencyDomain(
    val guid: String,
    val name: String
)
fun UrgencyDomain.toHuman() = UrgencyHuman(
    guid = guid,
    name = name
)

fun List<UrgencyDomain>.toHuman() = map { it.toHuman() }
