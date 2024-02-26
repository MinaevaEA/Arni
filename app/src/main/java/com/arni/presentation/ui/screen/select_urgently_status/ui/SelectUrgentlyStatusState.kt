package com.arni.presentation.ui.screen.select_urgently_status.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.UrgentlyHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectUrgentlyStatusState(
    val listUrgently: List<UrgentlyHuman> = persistentListOf()
) : BaseState

sealed interface SelectUrgentlyStatusEvent : BaseEvent {
    object OnBackCLickEvent : SelectUrgentlyStatusEvent

    class SelectUrgently(val urgently: UrgentlyHuman) :
        SelectUrgentlyStatusEvent
}

sealed interface SelectUrgentlyStatusAction : BaseAction {

    object OnExist : SelectUrgentlyStatusAction
}
