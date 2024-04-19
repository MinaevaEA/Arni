package com.arni.presentation.ui.screen.select_division_detail.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.DivisionRequestHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectSubdivisionState(
    val listSubdivision: List<DivisionHuman> = persistentListOf()
) : BaseState

sealed interface SelectSubDivisionEvent : BaseEvent {
    object OnBackCLickEvent : SelectSubDivisionEvent

    class SelectDivision(val subdivisionHuman: DivisionHuman) :
        SelectSubDivisionEvent
}

sealed interface SelectSubDivisionAction : BaseAction {

    object OnExist : SelectSubDivisionAction
}
