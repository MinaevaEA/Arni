package com.arni.presentation.ui.screen.request.general.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.UserHuman
import com.arni.presentation.ui.screen.request.general.GeneralRequestScreen
import okhttp3.internal.immutableListOf

@Immutable
data class GeneralRequestState(
    val tasks: List<RequestHuman> = immutableListOf(),
    val human: UserHuman = UserHuman.getDefault(),
    val isCreateRequest : Boolean = false
) : BaseState

sealed interface GeneralRequestEvent : BaseEvent {

    // object OnCreate : GeneralRequestEvent
    object OnClickAddRequest : GeneralRequestEvent
    object OnClickFilter : GeneralRequestEvent
    object OnBackBtnClick : GeneralRequestEvent

    data class onClickItem(val item: RequestHuman, val human: UserHuman) : GeneralRequestEvent
}

sealed interface GeneralRequestAction : BaseAction {
    data class OpenScreenDetailInfo(val item: RequestHuman, val user: UserHuman) : GeneralRequestAction
    object OpenScreenAddRequest : GeneralRequestAction
    object OpenScreenFilter : GeneralRequestAction
    object ExitScreen : GeneralRequestAction
}
