package com.arni.presentation.ui.screen.select_status_patient.ui


import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetPatientStatusUseCase
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.PatientStatusHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectStatusPatientViewModel(
    private val getPatientStatusUseCase: GetPatientStatusUseCase
) : BaseViewModel<SelectStatusPatientState, SelectStatusPatientEvent, SelectStatusPatientAction>(
    SelectStatusPatientState()
) {
    override fun obtainEvent(event: SelectStatusPatientEvent) {
        when (event) {

            SelectStatusPatientEvent.OnBackCLickEvent -> {
                // publishEvent(EventType.ShowHat(true))
                action = SelectStatusPatientAction.OnExist
            }

            else -> {}
        }
    }

    private val allStatus: MutableList<PatientStatusHuman> = mutableListOf()

    init {
        viewModelScope.launch {
            getPatientStatusUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }

                    is DataStatus.Success -> {
                        allStatus.clear()
                        allStatus.addAll(it.data)
                        viewState = viewState.copy(listPatientStatus = it.data)
                    }
                }
            }
        }
    }

    private fun selectCause() {

        viewModelScope.launch {}
        action = SelectStatusPatientAction.OnExist
    }
}

