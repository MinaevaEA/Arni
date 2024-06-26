package com.arni.remote.model.response

import com.arni.domain.model.ListRequestDomain
import com.google.gson.annotations.SerializedName

data class ListRequestResponse(
    @SerializedName("listid")
    val listId: String,
    @SerializedName("found")
    val found: Boolean,
    @SerializedName("itemspage")
    val itemsPage: List<RequestResponse>
)
fun ListRequestResponse.toDomain() = ListRequestDomain(
    listId = listId ?: "",
    found = found,
    itemsPage = itemsPage?.toDomain() ?: listOf()
)


