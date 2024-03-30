package com.arni.domain.model

import com.arni.presentation.model.human.ExecutorHuman


data class ExecutorDomain(
    val guid: String,
    val name: String
)
fun ExecutorDomain.toHuman() = ExecutorHuman(
    guid = guid,
    name = name
)
fun List<ExecutorDomain>.toHuman() = map { it.toHuman() }
