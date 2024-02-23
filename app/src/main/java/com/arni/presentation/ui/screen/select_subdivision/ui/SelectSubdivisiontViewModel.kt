package com.arni.presentation.ui.screen.select_subdivision.ui


import androidx.lifecycle.viewModelScope
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.launch

class SelectSubdivisionViewModel(
 /*   private val list: List<RequestStatusHuman>,
    private val index: Int*/
) : BaseViewModel<SelectSubdivisionState, SelectSubdivisionEvent, SelectSubdivisionAction>(
    SelectSubdivisionState(/*list.toImmutableList()*/)
) {
    override fun obtainEvent(event: SelectSubdivisionEvent) {
        when (event) {

            SelectSubdivisionEvent.OnBackCLickEvent -> {
               // publishEvent(EventType.ShowHat(true))
                action = SelectSubdivisionAction.OnExist
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
        action = SelectSubdivisionAction.OnExist

    }
}

