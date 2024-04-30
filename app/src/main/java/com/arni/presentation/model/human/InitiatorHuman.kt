package com.arni.presentation.model.human

import com.arni.remote.model.request.InitiatorRequest
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class InitiatorHuman(
    val guid: String,
    val name: String
) : Serializable {
    companion object {
        fun getDefault() = InitiatorHuman(
            guid = "",
            name = ""
        )
    }
}
fun InitiatorHuman.toRequest() = InitiatorRequest(
    guid = guid,
    name = name
)
