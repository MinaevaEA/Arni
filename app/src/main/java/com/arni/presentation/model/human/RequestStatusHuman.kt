package com.arni.presentation.model.human

import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class RequestStatusHuman(
    val guid: String,
    val name: String
) :Serializable
