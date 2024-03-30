package com.arni.presentation.model.human

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
