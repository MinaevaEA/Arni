package com.arni.presentation.model.human

import com.arni.remote.model.request.DispatcherRequest
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class DispatcherHuman(
    val guid: String,
    val name: String
) : Serializable {
    companion object {
        fun getDefault() = DispatcherHuman(
            guid = "",
            name = ""
        )
    }
}

fun DispatcherHuman.toRequest() = DispatcherRequest(
    guid = guid,
    name = name
)
