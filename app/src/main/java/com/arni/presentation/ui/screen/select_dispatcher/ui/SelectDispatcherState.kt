package com.arni.presentation.ui.screen.select_dispatcher.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DispatcherHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectDispatcherState(
    val listDispatcher: List<DispatcherHuman> = persistentListOf()
) : BaseState

sealed interface SelectDispatcherEvent : BaseEvent {
    object OnBackCLickEvent : SelectDispatcherEvent

    class SelectDispatcher(val dispatcher: DispatcherHuman) :
        SelectDispatcherEvent
}

sealed interface SelectDispatcherAction : BaseAction {

    object OnExist : SelectDispatcherAction
}
