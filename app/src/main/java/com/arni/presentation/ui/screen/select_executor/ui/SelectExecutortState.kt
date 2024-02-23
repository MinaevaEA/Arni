package com.arni.presentation.ui.screen.select_executor.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.SubdivisionHuman
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectExecutorState(
    val listSubdivision: ImmutableList<SubdivisionHuman> = persistentListOf()
) : BaseState


sealed interface SelectExecutorEvent : BaseEvent {
    object OnBackCLickEvent :
        SelectExecutorEvent

    class SelectSubDivision(val subdivisionHuman: SubdivisionHuman) :
        SelectExecutorEvent

}

sealed interface SelectExecutorAction : BaseAction {

    object OnExist : SelectExecutorAction


}
