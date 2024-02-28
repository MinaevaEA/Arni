package com.arni.presentation.ui.screen.select_executor.ui


import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.UserHuman
import kotlinx.coroutines.launch

class SelectExecutorViewModel(
private val list: List<UserHuman>
) : BaseViewModel<SelectExecutorState, SelectExecutorEvent, SelectExecutorAction>(
    SelectExecutorState(listExecutor = list)
) {
    override fun obtainEvent(event: SelectExecutorEvent) {
        when (event) {
            is SelectExecutorEvent.SelectExecutor -> {
                selectCause(event.user)
            }

            SelectExecutorEvent.OnBackCLickEvent -> {
                action = SelectExecutorAction.OnExist
            }
        }
    }

    private fun selectCause(executor: UserHuman) {
        viewModelScope.launch {
            Events.publish(EventType.OnExecutor(executor))
        }
        action = SelectExecutorAction.OnExist

    }
}

