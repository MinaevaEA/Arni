package com.arni.presentation.ui.screen.request.select_division_general.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DivisionHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectDivisionState(
    val listDivision: List<DivisionHuman> = persistentListOf(),
    val listRequestID: String = ""
) : BaseState

sealed interface SelectDivisionEvent : BaseEvent {
    object OnBackClickEvent : SelectDivisionEvent

    class SelectDivision(val divisionHuman: DivisionHuman, val listRequestID: String) :
        SelectDivisionEvent
}

sealed interface SelectDivisionAction : BaseAction {
    object OnExist : SelectDivisionAction
}
