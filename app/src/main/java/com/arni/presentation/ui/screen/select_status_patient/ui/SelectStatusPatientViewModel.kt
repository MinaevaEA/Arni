package com.arni.presentation.ui.screen.select_status_patient.ui


import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetPatientStatusUseCase
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.PatientStatusHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectStatusPatientViewModel(
    private val list: List<PatientStatusHuman>
) : BaseViewModel<SelectStatusPatientState, SelectStatusPatientEvent, SelectStatusPatientAction>(
    SelectStatusPatientState(listPatientStatus = list)
) {
    override fun obtainEvent(event: SelectStatusPatientEvent) {
        when (event) {
            is SelectStatusPatientEvent.SelectStatusPatient -> {
                selectCause(event.status)
            }

            SelectStatusPatientEvent.OnBackCLickEvent -> {
                action = SelectStatusPatientAction.OnExist
            }
        }
    }

    private fun selectCause(patientStatus: PatientStatusHuman) {

        viewModelScope.launch {
            Events.publish(EventType.OnStatusPatient(patientStatus))
        }
        action = SelectStatusPatientAction.OnExist
    }
}

