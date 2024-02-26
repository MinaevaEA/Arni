package com.arni.presentation.ui.screen.select_subdivision.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.SubdivisionHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectSubdivisionState(
    val listSubdivision: List<SubdivisionHuman> = persistentListOf()
) : BaseState

sealed interface SelectSubdivisionEvent : BaseEvent {
    object OnBackCLickEvent : SelectSubdivisionEvent

    class SelectSubDivision(val subdivisionHuman: SubdivisionHuman) :
        SelectSubdivisionEvent
}

sealed interface SelectSubdivisionAction : BaseAction {

    object OnExist : SelectSubdivisionAction
}
