package com.arni.presentation.ui.screen.select_urgently_status.ui


import androidx.lifecycle.viewModelScope
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.launch

class SelectUrgentlyStatusViewModel(
 /*   private val list: List<RequestStatusHuman>,
    private val index: Int*/
) : BaseViewModel<SelectUrgentlyStatusState, SelectUrgentlyStatusEvent, SelectUrgentlyStatusAction>(
    SelectUrgentlyStatusState(/*list.toImmutableList()*/)
) {
    override fun obtainEvent(event: SelectUrgentlyStatusEvent) {
        when (event) {

            SelectUrgentlyStatusEvent.OnBackCLickEvent -> {
               // publishEvent(EventType.ShowHat(true))
                action = SelectUrgentlyStatusAction.OnExist
            }

            else -> {}
        }
    }

    init {
       // publishEvent(EventType.ShowHat(false))
    }

    private fun selectCause(status: RequestStatusHuman) {

        viewModelScope.launch {
           // Events.publish()
        }
        action = SelectUrgentlyStatusAction.OnExist

    }
}

