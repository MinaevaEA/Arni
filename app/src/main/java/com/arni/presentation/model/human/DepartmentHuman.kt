package com.arni.presentation.model.human

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
