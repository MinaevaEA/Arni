package com.arni.presentation.model.human

import com.arni.remote.model.request.InitiatorRequest
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class InitiatorHuman(
    val guid: String = "00000000-0000-0000-0000-000000000000",
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
