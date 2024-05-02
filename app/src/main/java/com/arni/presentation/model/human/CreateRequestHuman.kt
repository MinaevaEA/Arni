package com.arni.presentation.model.human

import com.arni.remote.model.body.RequestItemBody
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Parcelize
data class CreateRequestHuman(
    val guid: String,
    var markdelete: Boolean,
    val statusRequest: RequestStatusHuman,
    val date: String = "${LocalDate.now()}",
    val number: String,
    val departamentFrom: DepartmentHuman,
    val departamentTo: DepartmentHuman,
    val startDate: String = "${LocalDate.now()}",
    val endDate: String = "${LocalDate.now()}'T'${LocalTime.now()}",
    val urgency: UrgencyHuman,
    val executors: List<ExecutorHuman>? = listOf(),
    val patients: List<PatientHuman>,
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
            date = "${LocalDate.now()}T${LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}",
            number = "",
            departamentFrom = DepartmentHuman("", ""),
            departamentTo = DepartmentHuman("", ""),
            startDate = "${LocalDate.now()}T${LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}",
            endDate = "${LocalDate.now()}T${LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}",
            urgency = UrgencyHuman("", ""),
            executors = listOf(),
            patients = listOf(),
            statusPatient = StatusPatientHuman("", ""),
            // val photos: List<String>,
            division = DivisionHuman("", "", "", listOf(), listOf(), listOf()),
            dispatcher = DispatcherHuman("", ""),
            initiator = InitiatorHuman("00000000-0000-0000-0000-000000000000", ""),
            comment = "",
        )
    }
}

fun CreateRequestHuman.toRequest() = RequestItemBody(
    guid = guid,
    markdelete = markdelete,
    statusRequest = statusRequest,
    date = date,
    number = number,
    departamentTo = departamentTo,
    departamentFrom = departamentFrom,
    startDate = startDate,
    endDate = endDate,
    urgency = urgency,
    executors = executors ?: listOf(),
    patients = patients,
    statusPatient = statusPatient,
    division = division,
    dispatcher = dispatcher,
    initiator = initiator,
    comment = comment
)
