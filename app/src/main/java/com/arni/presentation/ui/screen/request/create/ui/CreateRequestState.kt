package com.arni.presentation.ui.screen.request.create.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState

@Immutable
data class CreateRequestState(val detail: String): BaseState
sealed interface CreateRequestEvent : BaseEvent {

    object onClickBack: CreateRequestEvent
}
sealed interface CreateRequestAction : BaseAction {
    object returnGeneralScreen: CreateRequestAction
}
