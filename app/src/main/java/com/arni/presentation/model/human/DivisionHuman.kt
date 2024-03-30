package com.arni.presentation.model.human


data class DivisionHuman(
    val guid: String,
    val name: String,
    val role: String,
    val evecutors: List<ExecutorHuman>,
    val department: List<DepartmentHuman>,
) {
    companion object {
        fun getDefault() = DivisionHuman(
            guid = "",
            name = "",
            role = "",
            evecutors = listOf(),
            department = listOf()
        )
    }
}
