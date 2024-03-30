package com.arni.domain.model

import com.arni.presentation.model.human.DivisionHuman


data class DivisionDomain(
    val guid: String,
    val name: String,
    val role: String,
    val executors: List<ExecutorDomain>?,
    val department: List<DepartamentDomain>?,
)
fun DivisionDomain.toHuman() = DivisionHuman(
    guid = guid,
    name = name,
    role = role,
    evecutors = executors?.toHuman() ?: listOf(),
    department = department?.toHuman() ?: listOf()
)
fun List<DivisionDomain>.toHuman() = map { it.toHuman() }
