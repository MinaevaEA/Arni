package com.arni.presentation.ui.screen.select_executor.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.UserHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectExecutorState(
    val listExecutor: List<UserHuman> = persistentListOf()
) : BaseState


sealed interface SelectExecutorEvent : BaseEvent {
    object OnBackCLickEvent : SelectExecutorEvent

    class SelectExecutor(val user: UserHuman) :
        SelectExecutorEvent

}

sealed interface SelectExecutorAction : BaseAction {

    object OnExist : SelectExecutorAction

}
