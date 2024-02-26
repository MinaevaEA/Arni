package com.arni.presentation.model.human

data class UrgentlyHuman(
    val id: Int?,
    val title: String?
) {
    companion object {
        fun getDefault() = UrgentlyHuman(
            id = null,
            title = null
        )
    }
}
