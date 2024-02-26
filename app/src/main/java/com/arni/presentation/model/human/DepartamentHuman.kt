package com.arni.presentation.model.human

data class DepartamentHuman(
    val id: Int?,
    val title: String?
) {
    companion object {
        fun getDefault() = DepartamentHuman(
            id = null,
            title = null
        )
    }
}
