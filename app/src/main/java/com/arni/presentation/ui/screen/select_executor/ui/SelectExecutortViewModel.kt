package com.arni.presentation.ui.screen.select_executor.ui


import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetExecutorUseCase
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.UserHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectExecutorViewModel(
    private val getExecutorUseCase: GetExecutorUseCase
) : BaseViewModel<SelectExecutorState, SelectExecutorEvent, SelectExecutorAction>(
    SelectExecutorState()
) {
    override fun obtainEvent(event: SelectExecutorEvent) {
        when (event) {

            SelectExecutorEvent.OnBackCLickEvent -> {
                // publishEvent(EventType.ShowHat(true))
                action = SelectExecutorAction.OnExist
            }

            else -> {}
        }
    }

    private val allExecutor: MutableList<UserHuman> = mutableListOf()

    init {
        viewModelScope.launch {
            getExecutorUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }

                    is DataStatus.Success -> {
                        allExecutor.clear()
                        allExecutor.addAll(it.data)
                        viewState = viewState.copy(listExecutor = it.data)
                    }
                }
            }
        }
    }

    private fun selectCause(status: RequestStatusHuman) {

        viewModelScope.launch {}
        action = SelectExecutorAction.OnExist

    }
}

