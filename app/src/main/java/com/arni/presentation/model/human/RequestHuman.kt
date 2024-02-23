package com.arni.presentation.model.human

data class RequestHuman(
    val id: Long? = null,
    val statusRequest: Int? = null,
    val date: String? = null,
    val fromDepartament: String? = null,
    val toDepartament: String? = null,
    val beginTime: String? = null,
    val endTime: String? = null,
    val urgency: String? = null,
    val nameExecutor: String? = null,
    val namePatient: String? = null,
    val nameDispatcher: String? = null,
    val statusPatient: String? = null,
    val description: String? = null,
    val photo: List<String>? = null
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
            urgency = null,
            nameExecutor = null,
            namePatient = null,
            statusPatient = null,
            description = null,
            photo = null,
            nameDispatcher = null
        )
    }
}
