package com.arni.presentation.model.human

import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class CheckedHuman(
    val anotheritemver: Boolean
) : Serializable {
    companion object {
        fun getDefault() = CheckedHuman(
           anotheritemver = false)
    }
}
