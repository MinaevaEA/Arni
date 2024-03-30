package com.arni.presentation.ui.screen.select_executor.ui


import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.ExecutorHuman
import kotlinx.coroutines.launch

class SelectExecutorViewModel(
    private val list: List<ExecutorHuman>
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

    private fun selectCause(executor: List<ExecutorHuman>) {
        viewModelScope.launch {
            Events.publish(EventType.OnExecutor(executor))
        }
        action = SelectExecutorAction.OnExist
    }
}

