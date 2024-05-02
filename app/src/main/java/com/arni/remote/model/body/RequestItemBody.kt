package com.arni.remote.model.body

import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.model.human.DispatcherHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.ExecutorHuman
import com.arni.presentation.model.human.InitiatorHuman
import com.arni.presentation.model.human.PatientHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.model.human.UrgencyHuman

data class RequestItemBody(
    val guid: String,
    val markdelete: Boolean,
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
    val statusPatient: StatusPatientHuman,
// val photos: List<String>,
    val division: DivisionHuman,
    val dispatcher: DispatcherHuman,
    val initiator: InitiatorHuman,
    val comment: String
)

