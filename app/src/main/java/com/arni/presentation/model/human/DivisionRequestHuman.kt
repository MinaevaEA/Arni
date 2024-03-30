package com.arni.presentation.model.human

import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class DivisionRequestHuman(
    val guid: String,
    val name: String
) : Serializable {
    companion object {
        fun getDefault() = DivisionRequestHuman(
            guid = "",
            name = "",
        )
    }
}
