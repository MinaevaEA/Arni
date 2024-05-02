package com.arni.remote.model.response

import com.arni.presentation.model.human.CheckedHuman
import java.io.Serializable

data class CheckedResponse(
    val anotheritemver: Boolean
) : Serializable
    fun CheckedResponse.toHuman() = CheckedHuman(
        anotheritemver = anotheritemver
    )

