package com.arni.presentation.model.human

import com.arni.remote.model.request.StatusItemRequest
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class RequestStatusHuman(
    val guid: String,
    val name: String
) :Serializable

fun RequestStatusHuman.toRequest() = StatusItemRequest(
    guid = guid,
    name = name
)
