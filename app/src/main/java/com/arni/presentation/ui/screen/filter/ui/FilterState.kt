package com.arni.presentation.ui.screen.filter.ui


import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState


@Immutable
data class FilterState(
    val l: String = "",
) : BaseState

sealed interface FilterEvent : BaseEvent {

}

sealed interface FilterAction : BaseAction {

}
