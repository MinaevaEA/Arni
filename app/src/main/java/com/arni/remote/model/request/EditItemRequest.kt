package com.arni.remote.model.request

import com.google.gson.annotations.SerializedName

data class EditItemRequest(
    @SerializedName("guid")
    val guid: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("markdelete")
    val markdelete: Boolean,
    @SerializedName("number")
    val number: String?,
    @SerializedName("division")
    val division: DivisionRequest?,
    @SerializedName("departmentfrom")
    val departamentFrom: DepartmentRequest?,
    @SerializedName("departmentto")
    val departamentTo: DepartmentRequest?,
    @SerializedName("datestart")
    val dateStart: String?,
    @SerializedName("dateend")
    val dateEnd: String?,
    @SerializedName("dispatcher")
    val dispatcher: DispatcherRequest?,
    @SerializedName("urgency")
    val urgency: UrgencyRequest?,
    @SerializedName("initiator")
    val initiator: InitiatorRequest?,
    @SerializedName("statuspatient")
    val statusPatient: StatusPatientRequest?,
    @SerializedName("statusitem")
    val statusItem: StatusItemRequest?,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("patients")
    val patients: List<PatientRequest>?,
    @SerializedName("executors")
    val executor: List<ExecutorRequest>?,
)
