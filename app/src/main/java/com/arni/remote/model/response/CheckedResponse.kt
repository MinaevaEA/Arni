package com.arni.remote.model.response

import com.arni.domain.model.InitiatorDomain
import com.arni.presentation.model.human.CheckedHuman
import com.arni.presentation.model.human.InitiatorHuman
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CheckedResponse(
    val anotheritemver: Boolean
) : Serializable
    fun CheckedResponse.toHuman() = CheckedHuman(
        anotheritemver = anotheritemver
    )

