package com.arni.presentation.ui.screen.select_departament.ui


import androidx.lifecycle.viewModelScope
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.launch

class SelectDepartamentViewModel(
 /*   private val list: List<RequestStatusHuman>,
    private val index: Int*/
) : BaseViewModel<SelectDepartamentState, SelectDepartamentEvent, SelectDepartamentAction>(
    SelectDepartamentState(/*list.toImmutableList()*/)
) {
    override fun obtainEvent(event: SelectDepartamentEvent) {
        when (event) {

            SelectDepartamentEvent.OnBackCLickEvent -> {
               // publishEvent(EventType.ShowHat(true))
                action = SelectDepartamentAction.OnExist
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
        action = SelectDepartamentAction.OnExist

    }
}

