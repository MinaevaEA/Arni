package com.arni.presentation.ui.screen.select_division_detail.ui


import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects_delete.GetSubDivisionUseCase
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.DivisionRequestHuman
import com.arni.presentation.model.human.SubdivisionHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectSubdivisionViewModel(
    list: List<DivisionHuman>,
    private val getSubDivisionUseCase: GetSubDivisionUseCase
) : BaseViewModel<SelectSubdivisionState, SelectSubDivisionEvent, SelectSubDivisionAction>(
    SelectSubdivisionState(listSubdivision = list)
) {
    override fun obtainEvent(event: SelectSubDivisionEvent) {
        when (event) {
            is SelectSubDivisionEvent.SelectDivision -> {
                selectCause(event.subdivisionHuman)
            }

            SelectSubDivisionEvent.OnBackCLickEvent -> {
                action = SelectSubDivisionAction.OnExist
            }

            else -> {}
        }
    }

    private val allSubDivision: MutableList<SubdivisionHuman> = mutableListOf()

    init {
        viewModelScope.launch {
            getSubDivisionUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }

                    is DataStatus.Success -> {
                        allSubDivision.clear()
                        allSubDivision.addAll(it.data)
                        // viewState = viewState.copy(listSubdivision = it.data)
                    }
                }
            }
        }
    }

    private fun selectCause(division: DivisionHuman) {
        viewModelScope.launch {
            Events.publish(EventType.OnDivision(division))
        }
        action = SelectSubDivisionAction.OnExist

    }
}

