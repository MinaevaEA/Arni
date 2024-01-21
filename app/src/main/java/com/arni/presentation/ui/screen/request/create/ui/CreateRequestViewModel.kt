package com.arni.presentation.ui.screen.request.create.ui

import com.arni.presentation.base.BaseViewModel

class CreateRequestViewModel(
) : BaseViewModel<CreateRequestState, CreateRequestEvent, CreateRequestAction>(
    CreateRequestState(detail = "")
) {
    override fun obtainEvent(event: CreateRequestEvent) {
        when (event) {
            is CreateRequestEvent.onClickBack -> action = CreateRequestAction.returnGeneralScreen
        }
    }
}
