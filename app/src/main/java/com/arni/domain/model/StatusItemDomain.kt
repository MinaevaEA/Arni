package com.arni.domain.model

import com.arni.presentation.model.human.RequestStatusHuman

data class StatusItemDomain(
    val guid: String,
    val name: String
)
fun StatusItemDomain.toHuman() = RequestStatusHuman(
    guid = guid,
    name = name
)
fun List<StatusItemDomain>.toHuman() = map { it.toHuman() }
