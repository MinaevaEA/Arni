package com.arni.presentation.ui.screen.request.select_division_general.ui

import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DivisionHuman
import kotlinx.coroutines.launch

class SelectDivisionViewModel(
    private val list: List<DivisionHuman>,
    private val listID: String
) : BaseViewModel<SelectDivisionState, SelectDivisionEvent, SelectDivisionAction>(
    SelectDivisionState()
) {
    override fun obtainEvent(event: SelectDivisionEvent) {
        when (event) {
            is SelectDivisionEvent.OnBackClickEvent -> action = SelectDivisionAction.OnExist
            is SelectDivisionEvent.SelectDivision -> selectCause(event.divisionHuman, event.listRequestID)
        }
    }

    init {
        viewModelScope.launch {
            viewState = viewState.copy(listDivision = list, listRequestID = listID)
        }
    }

    private fun selectCause(divisionHuman: DivisionHuman, listID: String) {
        viewModelScope.launch {
            Events.publish(EventType.OnSelectDivisionGeneral(divisionHuman, listID))
        }
        action = SelectDivisionAction.OnExist
    }
}

