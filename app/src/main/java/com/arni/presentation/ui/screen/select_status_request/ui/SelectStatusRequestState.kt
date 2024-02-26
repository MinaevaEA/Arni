package com.arni.presentation.ui.screen.select_status_request.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SelectStatusRequestState(
    val listRequestStatus: List<RequestStatusHuman> = persistentListOf()
) : BaseState


sealed interface SelectStatusRequestEvent : BaseEvent {
    object OnBackCLickEvent :
        SelectStatusRequestEvent

    class SelectStatusRequest(val status: RequestStatusHuman) :
        SelectStatusRequestEvent

}

sealed interface SelectStatusRequestAction : BaseAction {

    object OnExist : SelectStatusRequestAction


}
