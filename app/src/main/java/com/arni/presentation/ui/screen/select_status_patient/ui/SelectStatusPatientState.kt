package com.arni.presentation.ui.screen.select_status_patient.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.StatusPatientHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectStatusPatientState(
    val listPatientStatus: List<StatusPatientHuman> = persistentListOf()
) : BaseState


sealed interface SelectStatusPatientEvent : BaseEvent {
    object OnBackCLickEvent : SelectStatusPatientEvent

    class SelectStatusPatient(val status: StatusPatientHuman) :
        SelectStatusPatientEvent
}

sealed interface SelectStatusPatientAction : BaseAction {

    object OnExist : SelectStatusPatientAction
}
