package com.arni.remote.model.response

import com.arni.domain.model.DepartamentDomain
import com.arni.domain.model.DispatcherDomain
import com.arni.domain.model.DivisionDomain
import com.arni.domain.model.DivisionRequestDomain
import com.arni.domain.model.InitiatorDomain
import com.arni.domain.model.RequestDomain
import com.arni.domain.model.StatusItemDomain
import com.arni.domain.model.StatusPatientDomain
import com.arni.domain.model.UrgencyDomain
import com.google.gson.annotations.SerializedName


data class RequestResponse(
    @SerializedName("guid")
    val guid: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("division")
    val division: DivisionResponse?,
    @SerializedName("departmentfrom")
    val departamentFrom: DepartamentResponse?,
    @SerializedName("departmentto")
    val departamentTo: DepartamentResponse?,
    @SerializedName("datestart")
    val dateStart: String?,
    @SerializedName("dateend")
    val dateEnd: String?,
    @SerializedName("dispatcher")
    val dispatcher: DispatcherResponse?,
    @SerializedName("urgency")
    val urgency: UrgencyResponse?,
    @SerializedName("initiator")
    val initiator: InitiatorResponse?,
    @SerializedName("statuspatient")
    val statusPatient: StatusPatientResponse?,
    @SerializedName("statusitem")
    val statusItem: StatusItemResponse?,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("patients")
    val patients: List<PatientResponse>?,
    @SerializedName("executors")
    val executor: List<ExecutorResponse>?,
)

fun RequestResponse.toDomain() = RequestDomain(
    guid = guid ?: "",
    date = date ?: "",
    number = number ?: "",
    division = division?.toDomain() ?: DivisionDomain("", "", "", listOf(), listOf()),
    departamentFrom = departamentFrom?.toDomain() ?: DepartamentDomain("", ""),
    departamentTo = departamentTo?.toDomain() ?: DepartamentDomain("", ""),
    dateStart = dateStart ?: "",
    dateEnd = dateEnd ?: "",
    dispatcher = dispatcher?.toDomain() ?: DispatcherDomain("", ""),
    urgency = urgency?.toDomain() ?: UrgencyDomain("", ""),
    initiator = initiator?.toDomain() ?: InitiatorDomain("", ""),
    statusPatient = statusPatient?.toDomain() ?: StatusPatientDomain("", ""),
    statusItem = statusItem?.toDomain() ?: StatusItemDomain("", ""),
    comment = comment ?: "",
    patients = patients?.toDomain() ?: listOf(),
    executor = executor?.toDomain() ?: listOf()

)

fun List<RequestResponse>.toDomain() = map { it.toDomain() }
