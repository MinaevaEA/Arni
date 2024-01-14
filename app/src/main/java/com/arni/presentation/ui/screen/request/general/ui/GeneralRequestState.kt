package com.arni.presentation.ui.screen.request.general.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import kotlinx.collections.immutable.PersistentList

@Immutable
data class GeneralRequestState(
    val tasks: PersistentList<String>,
): BaseState

sealed interface GeneralRequestEvent : BaseEvent {

    object OnCreate : GeneralRequestEvent
   data class OnClickItem(val item: String) : GeneralRequestEvent
    object OnBackBtnClick : GeneralRequestEvent
}

sealed interface GeneralRequestAction : BaseAction {
    data class OpenScreenDetailInfo(val item: String): GeneralRequestAction

    object ExitScreen: GeneralRequestAction
}
