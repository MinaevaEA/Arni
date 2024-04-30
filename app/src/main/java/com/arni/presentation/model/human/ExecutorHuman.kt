package com.arni.presentation.model.human

import com.arni.remote.model.request.ExecutorRequest
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class ExecutorHuman(
    val guid: String,
    val name: String
) : Serializable {
    companion object {
        fun getDefault() = ExecutorHuman(
            guid = "",
            name = ""
        )
    }
}

fun ExecutorHuman.toRequest() = ExecutorRequest(
    guid = guid,
    name = name
)

fun List<ExecutorHuman>.toRequest() = map { it.toRequest() }
