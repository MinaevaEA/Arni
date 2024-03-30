package com.arni.domain.model

import com.arni.presentation.model.human.InitiatorHuman


data class InitiatorDomain(
    val guid: String,
    val name: String
)
fun InitiatorDomain.toHuman() = InitiatorHuman(
    guid = guid,
    name = name
)

