package com.arni.domain.model

import com.arni.presentation.model.human.DivisionHuman


data class DivisionDomain(
    val guid: String,
    val name: String,
    val role: String? = "",
    val executors: List<ExecutorDomain>? = listOf(),
    val department: List<DepartamentDomain>? = listOf(),
    val dispatcher: List<DispatcherDomain>?
)

fun DivisionDomain.toHuman() = DivisionHuman(
    guid = guid,
    name = name,
    role = role ?: "",
    executors = executors?.toHuman() ?: listOf(),
    department = department?.toHuman() ?: listOf(),
    dispatcher = dispatcher?.toHuman() ?: listOf()
)

fun List<DivisionDomain>.toHuman() = map { it.toHuman() }
