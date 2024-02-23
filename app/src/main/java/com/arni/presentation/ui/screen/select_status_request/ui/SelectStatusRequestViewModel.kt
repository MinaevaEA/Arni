package com.arni.presentation.ui.screen.select_status_request.ui


import androidx.lifecycle.viewModelScope
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.launch

class SelectStatusRequestViewModel(
 /*   private val list: List<RequestStatusHuman>,
    private val index: Int*/
) : BaseViewModel<SelectStatusRequestState, SelectStatusRequestEvent, SelectStatusRequestAction>(
    SelectStatusRequestState(/*list.toImmutableList()*/)
) {
    override fun obtainEvent(event: SelectStatusRequestEvent) {
        when (event) {

            is SelectStatusRequestEvent.SelectStatusRequest -> {
               // publishEvent(EventType.ShowHat(true))
                selectCause(event.status)
            }

            SelectStatusRequestEvent.OnBackCLickEvent -> {
               // publishEvent(EventType.ShowHat(true))
                action = SelectStatusRequestAction.OnExist
            }

        }
    }

    init {
       // publishEvent(EventType.ShowHat(false))
    }

    private fun selectCause(status: RequestStatusHuman) {

        viewModelScope.launch {
           // Events.publish(EventType.SelectSport(sport, index))
        }
        action = SelectStatusRequestAction.OnExist

    }
}

