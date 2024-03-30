package com.arni.presentation.model.human

import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListRequestHuman(
    val listId: String,
    val itemsPage: List<RequestHuman>
) {
    companion object {
        fun getDefault() = ListRequestHuman(
           listId = "",
            itemsPage = listOf()
        )
    }
}
