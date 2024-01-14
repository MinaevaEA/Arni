package com.arni.presentation.ui.screen.general.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import kotlinx.collections.immutable.PersistentList

@Immutable
data class GeneralCheckingState(
    val tasks: PersistentList<String>,
): BaseState

sealed interface GeneralCheckingEvent : BaseEvent {

    object OnCreate : GeneralCheckingEvent
   data class OnClickItem(val item: String) : GeneralCheckingEvent
    object OnBackBtnClick : GeneralCheckingEvent
}

sealed interface GeneralCheckingAction : BaseAction {
    data class OpenScreenDetailInfo(val item: String): GeneralCheckingAction

    object ExitScreen: GeneralCheckingAction
}
