package com.arni.presentation.ui.screen.select_executor.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.ExecutorHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectExecutorState(
    val listExecutor: List<ExecutorHuman> = persistentListOf()
) : BaseState


sealed interface SelectExecutorEvent : BaseEvent {
    object OnBackCLickEvent : SelectExecutorEvent

    class SelectExecutor(val user: List<ExecutorHuman>) :
        SelectExecutorEvent
}

sealed interface SelectExecutorAction : BaseAction {

    object OnExist : SelectExecutorAction

}
