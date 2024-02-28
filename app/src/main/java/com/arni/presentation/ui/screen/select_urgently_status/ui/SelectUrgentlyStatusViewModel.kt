package com.arni.presentation.ui.screen.select_urgently_status.ui


import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetSelectUrgentlyUseCase
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.UrgentlyHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectUrgentlyStatusViewModel(
    private val list: List<UrgentlyHuman>
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


    private fun selectCause(urgentlyHuman: UrgentlyHuman) {

        viewModelScope.launch {
            Events.publish(EventType.OnUrgently(urgentlyHuman))
        }
        action = SelectUrgentlyStatusAction.OnExist

    }
}

