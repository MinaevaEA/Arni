package com.arni.presentation.ui.screen.request.detail.ui

import com.arni.presentation.base.BaseViewModel

class DetailRequestViewModel(
) : BaseViewModel<DetailRequestState, DetailRequestEvent, DetailRequestAction>(
    DetailRequestState(detail = "")
) {
    override fun obtainEvent(event: DetailRequestEvent) {
        TODO("Not yet implemented")
    }
}
