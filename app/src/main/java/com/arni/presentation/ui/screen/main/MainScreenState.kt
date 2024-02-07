package com.arni.presentation.ui.screen.main

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.ext.HideBottomMenuTrigger

@Immutable
data class MainScreenState(
    val changeVisibilityBottomMenuTrigger: HideBottomMenuTrigger? = null
) : BaseState

sealed interface MainScreenEvent : BaseEvent {

}

sealed interface MainScreenAction : BaseAction {

}
