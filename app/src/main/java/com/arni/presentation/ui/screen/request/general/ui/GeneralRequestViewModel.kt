package com.arni.presentation.ui.screen.request.general.ui

import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.request.GetRequestUseCase
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GeneralRequestViewModel(
    val getRequestUseCase: GetRequestUseCase
) : BaseViewModel<GeneralRequestState, GeneralRequestEvent, GeneralRequestAction>(
    GeneralRequestState()
) {

    val allRequest: MutableList<RequestHuman> = mutableListOf()

    override fun obtainEvent(event: GeneralRequestEvent) {
        when (event) {
            is GeneralRequestEvent.onClickItem -> action = GeneralRequestAction.OpenScreenDetailInfo(event.item)
            is GeneralRequestEvent.OnClickAddRequest -> action = GeneralRequestAction.OpenScreenAddRequest
            is GeneralRequestEvent.OnBackBtnClick -> action = GeneralRequestAction.ExitScreen
        }
    }

    init {
        viewModelScope.launch {
            getRequestUseCase.invoke().collectLatest {
                when (it) {
                    is DataStatus.Success -> {
                        allRequest.addAll(it.data)
                        viewState= viewState.copy(tasks = allRequest)
                    }

                    else -> {}
                }
            }
                Events.publish(EventType.ChangeVisibilityBottomMenu(false))
        }
    }

}
