package com.arni.presentation.ui.screen.request.select_division.ui


import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DivisionHuman
import kotlinx.coroutines.launch

class SelectDivisionViewModel(
    private val list: List<DivisionHuman>
) : BaseViewModel<SelectDivisionState, SelectDivisionEvent, SelectDivisionAction>(
    SelectDivisionState()
) {
    override fun obtainEvent(event: SelectDivisionEvent) {
        when (event) {

            SelectDivisionEvent.OnBackCLickEvent -> {
                action = SelectDivisionAction.OnExist
            }

            is SelectDivisionEvent.SelectDivision -> selectCause(event.divisionHuman)
        }
    }

    init {
        viewModelScope.launch {
            viewState = viewState.copy(listDivision = list)
        }
    }

    private fun selectCause(divisionHuman: DivisionHuman) {
        viewModelScope.launch {
            Events.publish(EventType.OnDivision(divisionHuman))
        }
        action = SelectDivisionAction.OnExist
    }
}

