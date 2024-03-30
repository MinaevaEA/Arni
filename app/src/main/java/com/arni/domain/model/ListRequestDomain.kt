package com.arni.domain.model

import com.arni.presentation.model.human.ListRequestHuman

data class ListRequestDomain(
    val listId: String,
    val itemsPage: List<RequestDomain>
)
fun ListRequestDomain.toHuman() = ListRequestHuman(
    listId = listId,
    itemsPage = itemsPage.toHuman()
)
