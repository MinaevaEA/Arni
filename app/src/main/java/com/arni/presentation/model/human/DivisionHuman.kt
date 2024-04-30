package com.arni.presentation.model.human

import com.arni.remote.model.request.DivisionRequest


data class DivisionHuman(
    val guid: String,
    val name: String,
    val role: String,
    val executors: List<ExecutorHuman>,
    var department: List<DepartmentHuman>,
) {
    companion object {
        fun getDefault() = DivisionHuman(
            guid = "",
            name = "",
            role = "",
            executors = listOf(),
            department = listOf()
        )
    }
}
fun DivisionHuman.toRequest() = DivisionRequest(
    guid = guid,
    name = name,
    role = role,
    executors = executors.toRequest(),
    department = department.toRequest()
)
