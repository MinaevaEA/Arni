package com.arni.presentation.model.human

import com.arni.remote.model.request.UrgencyRequest
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class UrgencyHuman(
    val guid: String,
    val name: String
) : Serializable {
    companion object {
        fun getDefault() = UrgencyHuman(
            guid = "",
            name = ""
        )
    }
}

fun UrgencyHuman.toRequest() = UrgencyRequest(
    guid = guid,
    name = name
)
