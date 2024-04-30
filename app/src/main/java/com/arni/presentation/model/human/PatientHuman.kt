package com.arni.presentation.model.human

import com.arni.remote.model.request.PatientRequest
import com.arni.remote.model.request.StatusItemRequest
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class PatientHuman(
    val name: String
) : Serializable {
    companion object {
        fun getDefault() = PatientHuman(
            name = ""
        )
    }
}

fun PatientHuman.toRequest() = PatientRequest(
    name = name
)

fun List<PatientHuman>.toRequest() = map { it.toRequest() }
