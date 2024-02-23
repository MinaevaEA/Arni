package com.arni.presentation.ui.screen.select_urgently_status.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.SubdivisionHuman
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectUrgentlyStatusState(
    val listSubdivision: ImmutableList<SubdivisionHuman> = persistentListOf()
) : BaseState


sealed interface SelectUrgentlyStatusEvent : BaseEvent {
    object OnBackCLickEvent :
        SelectUrgentlyStatusEvent

    class SelectSubDivision(val subdivisionHuman: SubdivisionHuman) :
        SelectUrgentlyStatusEvent

}

sealed interface SelectUrgentlyStatusAction : BaseAction {

    object OnExist : SelectUrgentlyStatusAction


}
