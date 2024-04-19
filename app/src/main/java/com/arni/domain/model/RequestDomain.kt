package com.arni.domain.model

import com.arni.presentation.model.human.RequestHuman
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestDomain(
    val guid: String,
    val date: String,
    val number: String,
    val division: DivisionDomain,
    val departamentFrom: DepartamentDomain,
    val departamentTo: DepartamentDomain,
    val dateStart: String,
    val dateEnd: String,
    val dispatcher: DispatcherDomain,
    val urgency: UrgencyDomain,
    val initiator: InitiatorDomain,
    val statusPatient: StatusPatientDomain,
    val statusItem: StatusItemDomain,
    val comment: String,
    val patients: List<PatientDomain>,
    val executor: List<ExecutorDomain>,
)

fun RequestDomain.toHuman() = RequestHuman(
    guid = guid,
    date = date,
    number = number,
    division = division.toHuman(),
    departamentFrom = departamentFrom.toHuman(),
    departamentTo = departamentTo.toHuman(),
    startDate = dateStart,
    endDate = dateEnd,
    dispatcher = dispatcher.toHuman(),
    urgency = urgency.toHuman(),
    initiator = initiator.toHuman(),
    statusPatient = statusPatient.toHuman(),
    statusRequest = statusItem.toHuman(),
    comment = comment,
    patients = patients.toHuman(),
    executors = executor.toHuman(),

)
fun List<RequestDomain>.toHuman() = map { it.toHuman() }
