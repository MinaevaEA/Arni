package com.arni.presentation.ui.screen.select_urgently_status.ui


import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.UrgencyHuman
import kotlinx.coroutines.launch

class SelectUrgentlyStatusViewModel(
    private val list: List<UrgencyHuman>
) : BaseViewModel<SelectUrgentlyStatusState, SelectUrgentlyStatusEvent, SelectUrgentlyStatusAction>(
    SelectUrgentlyStatusState(listUrgently = list)
) {
    override fun obtainEvent(event: SelectUrgentlyStatusEvent) {
        when (event) {
            is SelectUrgentlyStatusEvent.SelectUrgently -> {
                selectCause(event.urgently)
            }

            SelectUrgentlyStatusEvent.OnBackCLickEvent -> {
                action = SelectUrgentlyStatusAction.OnExist
            }

            else -> {}
        }
    }


    private fun selectCause(urgentlyHuman: UrgencyHuman) {

        viewModelScope.launch {
            Events.publish(EventType.OnUrgently(urgentlyHuman))
        }
        action = SelectUrgentlyStatusAction.OnExist

    }
}

