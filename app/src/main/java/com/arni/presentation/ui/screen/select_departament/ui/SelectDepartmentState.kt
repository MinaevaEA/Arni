package com.arni.presentation.ui.screen.select_departament.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DepartmentHuman


// TODO remove this file
@Immutable
sealed interface SelectDepartmentState: BaseState

object Loading: SelectDepartmentState
class Content(
    val departments: List<DepartmentHuman> = mutableListOf()
) : SelectDepartmentState

sealed interface SelectDepartmentEvent : BaseEvent {
    object OnBackCLickEvent : SelectDepartmentEvent

    class SelectDepartment(val department: DepartmentHuman) :
        SelectDepartmentEvent
}

sealed interface SelectDepartmentAction : BaseAction {

    object OnExist : SelectDepartmentAction
}
