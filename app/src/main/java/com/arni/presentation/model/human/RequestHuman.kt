package com.arni.presentation.model.human

import androidx.compose.runtime.Immutable

@Immutable
data class RequestHuman(
    val id: Long? = null,
    val statusRequest: RequestStatusHuman? = null,
    val date: String? = null,
    val fromDepartament: String? = null,
    val toDepartament: String? = null,
    val beginTime: String? = null,
    val endTime: String? = null,
    val urgently: UrgentlyHuman? = null,
    val nameExecutor: UserHuman? = null,
    val namePatient: String? = null,
    val nameDispatcher: String? = null,
    val statusPatient: PatientStatusHuman? = null,
    val description: String? = null,
    val photos: List<String>
) {

    companion object {

        fun getDefault() = RequestHuman(
            id = null,
            statusRequest = null,
            date = null,
            fromDepartament = null,
            toDepartament = null,
            beginTime = null,
            endTime = null,
            urgently = null,
            nameExecutor = null,
            namePatient = null,
            statusPatient = null,
            description = null,
            photos = listOf(),
            nameDispatcher = null
        )
    }
}
