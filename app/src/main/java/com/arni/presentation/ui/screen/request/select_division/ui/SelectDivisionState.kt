package com.arni.presentation.ui.screen.request.select_division.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DivisionHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectDivisionState(
    val listDivision: List<DivisionHuman> = persistentListOf()
) : BaseState

sealed interface SelectDivisionEvent : BaseEvent {
    object OnBackCLickEvent : SelectDivisionEvent

    class SelectDivision(val divisionHuman: DivisionHuman) :
        SelectDivisionEvent
}

sealed interface SelectDivisionAction : BaseAction {
//TODO разобраться с открытием БШ
    object OnExist : SelectDivisionAction
}
