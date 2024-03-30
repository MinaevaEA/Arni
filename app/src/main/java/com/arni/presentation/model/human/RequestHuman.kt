package com.arni.presentation.model.human

import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class RequestHuman(
    val guid: String,
    val statusRequest: RequestStatusHuman,
    val date: String,
    val number: String,
    val departamentFrom: DepartmentHuman,
    val departamentTo: DepartmentHuman,
    val startDate: String,
    val endDate: String,
    val urgency: UrgencyHuman,
    val executors: List<ExecutorHuman>,
    val patients: List<PatientHuman>,
    val nameDispatcher: String? = null,
    val statusPatient: StatusPatientHuman,
    val description: String? = null,
    // val photos: List<String>,
    val division: DivisionRequestHuman,
    val dispatcher: DispatcherHuman,
    val initiator: InitiatorHuman,
    val comment: String,

    ) : Serializable {

    companion object {
        fun getDefault() = RequestHuman(
            guid = "",
            statusRequest = RequestStatusHuman("", ""),
            date = "",
            number = "",
            departamentFrom = DepartmentHuman("", ""),
            departamentTo = DepartmentHuman("", ""),
            startDate = "",
            endDate = "",
            urgency = UrgencyHuman("", ""),
            executors = listOf(),
            patients = listOf(),
            nameDispatcher = "",
            statusPatient = StatusPatientHuman("", ""),
            description = "",
            // val photos: List<String>,
            division = DivisionRequestHuman("", ""),
            dispatcher = DispatcherHuman("", ""),
            initiator = InitiatorHuman("", ""),
            comment = "",
        )
    }
}
