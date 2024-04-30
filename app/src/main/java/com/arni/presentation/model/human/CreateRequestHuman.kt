package com.arni.presentation.model.human

import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class CreateRequestHuman(
    val guid: String,
    var markdelete: Boolean,
    val statusRequest: RequestStatusHuman,
    val date: String = "${LocalDate.now()}'T'${LocalTime.now()}",
    val number: String,
    val departamentFrom: DepartmentHuman,
    val departamentTo: DepartmentHuman,
    val startDate: String = "${LocalDate.now()}",
    val endDate: String = "${LocalDate.now()}'T'${LocalTime.now()}",
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
        fun getDefault() = CreateRequestHuman(
            guid = "",
            markdelete = false,
            statusRequest = RequestStatusHuman("", ""),
            date = "${LocalDate.now()}T${LocalTime.now()}",
            number = "",
            departamentFrom = DepartmentHuman("", ""),
            departamentTo = DepartmentHuman("", ""),
            startDate = "${LocalDate.now()}T${LocalTime.now()}",
            endDate = "${LocalDate.now()}'T'${LocalTime.now()}",
            urgency = UrgencyHuman("", ""),
            executors = listOf(),
            patients = listOf(),
            nameDispatcher = "",
            statusPatient = StatusPatientHuman("", ""),
            description = "",
            // val photos: List<String>,
            division = DivisionHuman("", "", "", listOf(), listOf()),
            dispatcher = DispatcherHuman("", ""),
            initiator = InitiatorHuman("", ""),
            comment = "",
        )
    }
}
