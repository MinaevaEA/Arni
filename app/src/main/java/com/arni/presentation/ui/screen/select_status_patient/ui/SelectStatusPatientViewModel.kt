package com.arni.presentation.ui.screen.select_status_patient.ui


import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.StatusPatientHuman
import kotlinx.coroutines.launch

class SelectStatusPatientViewModel(
    private val list: List<StatusPatientHuman>
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

    private fun selectCause(patientStatus: StatusPatientHuman) {

        viewModelScope.launch {
            Events.publish(EventType.OnStatusPatient(patientStatus))
        }
        action = SelectStatusPatientAction.OnExist
    }
}

