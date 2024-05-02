package com.arni.presentation.model.human

import kotlinx.parcelize.Parcelize

@Parcelize
data class ListRequestHuman(
    val listId: String,
    val found : Boolean,
    val itemsPage: List<RequestHuman>
)
