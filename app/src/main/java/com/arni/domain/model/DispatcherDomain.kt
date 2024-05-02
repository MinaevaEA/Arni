package com.arni.domain.model

import com.arni.presentation.model.human.DispatcherHuman


data class DispatcherDomain(
    val guid: String,
    val name: String
)
fun DispatcherDomain.toHuman() = DispatcherHuman(
    guid = guid,
    name = name
)
fun List<DispatcherDomain>.toHuman() = map { it.toHuman()}
