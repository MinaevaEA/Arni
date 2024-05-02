package com.arni.presentation.model.human

import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class RequestHuman(
    val guid: String,
    var markdelete: Boolean,
    val statusRequest: RequestStatusHuman,
    val date: String,
    val number: String,
    val departamentFrom: DepartmentHuman,
    val departamentTo: DepartmentHuman,
    val startDate: String,
    val endDate: String,
    val urgency: UrgencyHuman,
    val executors: List<ExecutorHuman>? = listOf() ,
    val patients: List<PatientHuman>,
    val nameDispatcher: String? = "",
    val statusPatient: StatusPatientHuman,
    val description: String? = "",
    // val photos: List<String>,
    val division: DivisionHuman,
    val dispatcher: DispatcherHuman,
    val initiator: InitiatorHuman,
    val comment: String,

    ) : Serializable {

    companion object {
        fun getDefault() = RequestHuman(
            guid = "",
            markdelete = false,
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
            division = DivisionHuman("", "", "", listOf(), listOf(), listOf()),
            dispatcher = DispatcherHuman("", ""),
            initiator = InitiatorHuman("", ""),
            comment = "",
        )
    }
}
