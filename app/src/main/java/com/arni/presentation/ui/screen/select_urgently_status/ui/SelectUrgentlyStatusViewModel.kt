package com.arni.presentation.ui.screen.select_urgently_status.ui


import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetSelectUrgentlyUseCase
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.UrgentlyHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectUrgentlyStatusViewModel(
    private val getSelectUrgentlyUseCase: GetSelectUrgentlyUseCase
) : BaseViewModel<SelectUrgentlyStatusState, SelectUrgentlyStatusEvent, SelectUrgentlyStatusAction>(
    SelectUrgentlyStatusState()
) {
    override fun obtainEvent(event: SelectUrgentlyStatusEvent) {
        when (event) {

            SelectUrgentlyStatusEvent.OnBackCLickEvent -> {
                // publishEvent(EventType.ShowHat(true))
                action = SelectUrgentlyStatusAction.OnExist
            }

            else -> {}
        }
    }

    private val allUrgently: MutableList<UrgentlyHuman> = mutableListOf()

    init {
        viewModelScope.launch {
            getSelectUrgentlyUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }

                    is DataStatus.Success -> {
                        allUrgently.clear()
                        allUrgently.addAll(it.data)
                        viewState = viewState.copy(listUrgently = it.data)
                    }
                }
            }
        }
    }

    private fun selectCause() {

        viewModelScope.launch {
            // Events.publish()
        }
        action = SelectUrgentlyStatusAction.OnExist

    }
}

