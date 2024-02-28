package com.arni.presentation.ui.screen.select_status_request.ui


import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.launch

class SelectStatusRequestViewModel(
    private val list: List<RequestStatusHuman>
) : BaseViewModel<SelectStatusRequestState, SelectStatusRequestEvent, SelectStatusRequestAction>(
    SelectStatusRequestState(listRequestStatus = list)
) {
    override fun obtainEvent(event: SelectStatusRequestEvent) {
        when (event) {

            is SelectStatusRequestEvent.SelectStatusRequest -> {
                selectCause(event.status)
            }

            SelectStatusRequestEvent.OnBackCLickEvent -> {
                action = SelectStatusRequestAction.OnExist
            }
        }
    }


    private fun selectCause(statusHuman: RequestStatusHuman) {

        viewModelScope.launch {
            Events.publish(EventType.OnStatusRequest(statusHuman))
        }
        action = SelectStatusRequestAction.OnExist
    }
}

