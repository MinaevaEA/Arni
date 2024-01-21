package com.arni.presentation.ui.screen.profile.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState

@Immutable
data class ProfileState(val v:String): BaseState

sealed interface ProfileEvent: BaseEvent{

}

sealed interface ProfileAction: BaseAction{

}
