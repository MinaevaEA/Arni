package com.arni.presentation.model.human

import com.arni.remote.model.request.DepartmentRequest

data class DepartmentHuman(
    val guid: String,
    val name: String,
) {
    companion object {
        fun getDefault() = DepartmentHuman(
            guid = "",
            name = "",
        )
    }
}

fun DepartmentHuman.toRequest() = DepartmentRequest(
    guid = guid,
    name = name
)

fun List<DepartmentHuman>.toRequest() = map { it.toRequest() }
