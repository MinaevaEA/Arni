package com.arni.presentation.ui.screen.request.detail.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.RequestHuman

@Immutable
data class DetailRequestState(val item: RequestHuman = RequestHuman.getDefault(), val enabled: Boolean = false)
    :BaseState

sealed interface DetailRequestEvent : BaseEvent {

    object onClickBackList : DetailRequestEvent
}

sealed interface DetailRequestAction : BaseAction {
    object returnScreenList : DetailRequestAction
}
