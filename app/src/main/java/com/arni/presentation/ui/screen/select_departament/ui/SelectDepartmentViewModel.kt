package com.arni.presentation.ui.screen.select_departament.ui

import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DepartmentHuman
import kotlinx.coroutines.launch

class SelectDepartmentViewModel(
    //var isDepFrom: Boolean,
    //TODO remove this file?
    //var list: List<DepartmentHuman>,
) : BaseViewModel<SelectDepartmentState, SelectDepartmentEvent, SelectDepartmentAction>(Loading) {
    override fun obtainEvent(event: SelectDepartmentEvent) {
        when (event) {
            is SelectDepartmentEvent.SelectDepartment -> selectCause(event.department)
            is SelectDepartmentEvent.OnBackCLickEvent -> {
                action = SelectDepartmentAction.OnExist
            }
        }
    }

    //init {
        //println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!list: $list")
        //println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!isDepFrom: $isDepFrom")

        //viewModelScope.launch {
        //    viewState = viewState.copy(departments = list)
        //}
    //}

    private fun selectCause(department: DepartmentHuman) {
        subscribeEvent<EventType.OnChangeAllDepartment> {
            //isDepFrom = it.depatmentTo
        }
        viewModelScope.launch {
//            if (isDepFrom)
//                Events.publish(EventType.OnDepatmentFrom(department))
//            else
//                Events.publish(EventType.OnDepatmentTo(department))
        }
        action = SelectDepartmentAction.OnExist
    }
}
