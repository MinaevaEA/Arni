package com.arni.presentation.ui.screen.request.create.ui

import com.arni.presentation.base.BaseViewModel
import java.time.LocalDate
import java.time.LocalTime

class CreateRequestViewModel(
) : BaseViewModel<CreateRequestState, CreateRequestEvent, CreateRequestAction>(
    CreateRequestState(detail = "")
) {
    override fun obtainEvent(event: CreateRequestEvent) {
        when (event) {
            is CreateRequestEvent.onClickBack -> action = CreateRequestAction.returnGeneralScreen
            CreateRequestEvent.onClickSelectorTime -> action = CreateRequestAction.OpenTimePicker(ACT_TIME_ID,
                LocalTime.now(), true)
        }
    }
    companion object {
        const val ACT_TIME_ID = 1
        const val ACT_DATE_ID = 2
    }
}
