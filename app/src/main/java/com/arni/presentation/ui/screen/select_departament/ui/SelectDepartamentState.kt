package com.arni.presentation.ui.screen.select_departament.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DepartmentHuman
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectDepartamentState(
    val listDepartament: List<DepartmentHuman> = persistentListOf()
) : BaseState

sealed interface SelectDepartamentEvent : BaseEvent {
    object OnBackCLickEvent : SelectDepartamentEvent

    class SelectSubDivision(val subdivisionHuman: DepartmentHuman) :
        SelectDepartamentEvent
}

sealed interface SelectDepartamentAction : BaseAction {

    object OnExist : SelectDepartamentAction

}
