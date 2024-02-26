package com.arni.presentation.ui.screen.select_departament.ui


import androidx.lifecycle.viewModelScope
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DepartamentHuman
import kotlinx.coroutines.launch

class SelectDepartamentViewModel(
    private val list: List<DepartamentHuman>
) : BaseViewModel<SelectDepartamentState, SelectDepartamentEvent, SelectDepartamentAction>(
    SelectDepartamentState(listDepartament = list)
) {
    override fun obtainEvent(event: SelectDepartamentEvent) {
        when (event) {

            SelectDepartamentEvent.OnBackCLickEvent -> {
                // publishEvent(EventType.ShowHat(true))
                action = SelectDepartamentAction.OnExist
            }

            else -> {}
        }
    }

    init {
        viewModelScope.launch {
            viewState = viewState.copy(listDepartament = list)
        }
    }

    private fun selectCause() {
        viewModelScope.launch {}
        action = SelectDepartamentAction.OnExist
    }
}

