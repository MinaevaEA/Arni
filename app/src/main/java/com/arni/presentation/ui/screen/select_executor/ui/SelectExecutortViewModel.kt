package com.arni.presentation.ui.screen.select_executor.ui


import androidx.lifecycle.viewModelScope
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.launch

class SelectExecutorViewModel(
 /*   private val list: List<RequestStatusHuman>,
    private val index: Int*/
) : BaseViewModel<SelectExecutorState, SelectExecutorEvent, SelectExecutorAction>(
    SelectExecutorState(/*list.toImmutableList()*/)
) {
    override fun obtainEvent(event: SelectExecutorEvent) {
        when (event) {

            SelectExecutorEvent.OnBackCLickEvent -> {
               // publishEvent(EventType.ShowHat(true))
                action = SelectExecutorAction.OnExist
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
        action = SelectExecutorAction.OnExist

    }
}

