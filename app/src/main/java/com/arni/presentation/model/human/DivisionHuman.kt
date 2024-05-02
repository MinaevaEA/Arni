package com.arni.presentation.model.human

import com.arni.remote.model.request.DivisionRequest


data class DivisionHuman(
    val guid: String,
    val name: String,
    val role: String?,
    val executors: List<ExecutorHuman>?,
    var department: List<DepartmentHuman>?,
    val dispatcher: List<DispatcherHuman>?
) {
    companion object {
        fun getDefault() = DivisionHuman(
            guid = "",
            name = "",
            role = "",
            executors = listOf(),
            department = listOf(),
            listOf()
        )
    }
}
fun DivisionHuman.toRequest() = DivisionRequest(
    guid = guid,
    name = name,
    role = role,
    executors = executors?.toRequest(),
    department = department?.toRequest()
)

fun DivisionHuman.toNewRequest() = DivisionRequest(
    guid = guid,
    name = name,
    null,null,null
)
