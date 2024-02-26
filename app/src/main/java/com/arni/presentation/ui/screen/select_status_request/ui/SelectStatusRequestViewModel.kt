package com.arni.presentation.ui.screen.select_status_request.ui


import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetSelectStatusRequestUseCase
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectStatusRequestViewModel(
private val getSelectStatusRequestUseCase: GetSelectStatusRequestUseCase
) : BaseViewModel<SelectStatusRequestState, SelectStatusRequestEvent, SelectStatusRequestAction>(
    SelectStatusRequestState()
) {
    override fun obtainEvent(event: SelectStatusRequestEvent) {
        when (event) {

            is SelectStatusRequestEvent.SelectStatusRequest -> {
               // publishEvent(EventType.ShowHat(true))
               // selectCause(event.status)
            }

            SelectStatusRequestEvent.OnBackCLickEvent -> {
               // publishEvent(EventType.ShowHat(true))
                action = SelectStatusRequestAction.OnExist
            }

        }
    }
    private val allRequestStatus: MutableList<RequestStatusHuman> = mutableListOf()
    init {
        viewModelScope.launch {
            getSelectStatusRequestUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }
                    is DataStatus.Success -> {
                        allRequestStatus.clear()
                        allRequestStatus.addAll(it.data)
                        viewState = viewState.copy(listRequestStatus = it.data)
                    }
                }
            }
        }
    }

    private fun selectCause() {

        viewModelScope.launch {
           // Events.publish(EventType.SelectSport(sport, index))
        }
        action = SelectStatusRequestAction.OnExist

    }
}

