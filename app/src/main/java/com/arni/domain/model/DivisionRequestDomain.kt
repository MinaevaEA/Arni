package com.arni.domain.model

import com.arni.presentation.model.human.DivisionRequestHuman


data class DivisionRequestDomain(
    val guid: String,
    val name: String,
)
fun DivisionRequestDomain.toHuman() = DivisionRequestHuman(
    guid = guid,
    name = name
)
fun List<DivisionRequestDomain>.toHuman() = map { it.toHuman() }
