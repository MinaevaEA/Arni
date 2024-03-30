package com.arni.presentation.ui.screen.select_subdivision.ui


import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetSubDivisionUseCase
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.SubdivisionHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectSubdivisionViewModel(
    private val getSubDivisionUseCase: GetSubDivisionUseCase
) : BaseViewModel<SelectSubdivisionState, SelectSubDivisionEvent, SelectSubDivisionAction>(
    SelectSubdivisionState()
) {
    override fun obtainEvent(event: SelectSubDivisionEvent) {
        when (event) {

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
                        viewState = viewState.copy(listSubdivision = it.data)
                    }
                }
            }
        }
    }

    private fun selectCause() {
        viewModelScope.launch {
            // Events.publish(EventType.SelectSport(sport, index))
        }
        action = SelectSubDivisionAction.OnExist

    }
}

