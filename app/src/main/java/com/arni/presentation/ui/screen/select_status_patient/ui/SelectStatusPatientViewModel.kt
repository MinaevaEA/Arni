package com.arni.presentation.ui.screen.select_status_patient.ui


import androidx.lifecycle.viewModelScope
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.launch

class SelectStatusPatientViewModel(
 /*   private val list: List<RequestStatusHuman>,
    private val index: Int*/
) : BaseViewModel<SelectStatusPatientState, SelectStatusPatientEvent, SelectStatusPatientAction>(
    SelectStatusPatientState(/*list.toImmutableList()*/)
) {
    override fun obtainEvent(event: SelectStatusPatientEvent) {
        when (event) {

            SelectStatusPatientEvent.OnBackCLickEvent -> {
               // publishEvent(EventType.ShowHat(true))
                action = SelectStatusPatientAction.OnExist
            }

            else -> {}
        }
    }

    init {
       // publishEvent(EventType.ShowHat(false))
    }

    private fun selectCause(status: RequestStatusHuman) {

        viewModelScope.launch {
           // Events.publish(EventType.SelectSport(sport, index))
        }
        action = SelectStatusPatientAction.OnExist

    }
}

