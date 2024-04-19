package com.arni.remote.model.response

import com.arni.domain.model.EditRequestDomain
import com.google.gson.annotations.SerializedName

data class EditRequestResponse(
    @SerializedName("item")
    val item: RequestResponse
)

fun EditRequestResponse.toDomain() = EditRequestDomain(
    item = item.toDomain()
)


