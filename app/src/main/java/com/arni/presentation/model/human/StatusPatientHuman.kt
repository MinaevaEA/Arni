package com.arni.presentation.model.human

import com.arni.remote.model.request.StatusPatientRequest
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class StatusPatientHuman(
    val guid: String,
    val name: String
) : Serializable {
    companion object {
        fun getDefault() = StatusPatientHuman(
            guid = "",
            name = ""
        )
    }
}
fun StatusPatientHuman.toRequest() = StatusPatientRequest(
    guid = guid,
    name = name
)
