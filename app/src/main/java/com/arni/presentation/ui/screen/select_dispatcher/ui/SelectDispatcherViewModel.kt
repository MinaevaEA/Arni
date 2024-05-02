package com.arni.presentation.ui.screen.select_dispatcher.ui


import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DispatcherHuman
import kotlinx.coroutines.launch

class SelectDispatcherViewModel(
    private val list: List<DispatcherHuman>
) : BaseViewModel<SelectDispatcherState, SelectDispatcherEvent, SelectDispatcherAction>(
    SelectDispatcherState(listDispatcher = list)
) {
    override fun obtainEvent(event: SelectDispatcherEvent) {
        when (event) {
            is SelectDispatcherEvent.SelectDispatcher -> {
                selectCause(event.dispatcher)
            }

            SelectDispatcherEvent.OnBackCLickEvent -> {
                action = SelectDispatcherAction.OnExist
            }

            else -> {}
        }
    }


    private fun selectCause(urgentlyHuman: DispatcherHuman) {

        viewModelScope.launch {
            Events.publish(EventType.OnDispatcher(urgentlyHuman))
        }
        action = SelectDispatcherAction.OnExist

    }
}

