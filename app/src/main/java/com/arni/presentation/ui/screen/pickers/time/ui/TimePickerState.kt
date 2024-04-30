package com.arni.presentation.ui.screen.pickers.time.ui

import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


data class TimePickerState(
    val hoursList: List<Int> = listOf(),
    val minutesList: List<Int> = listOf(),
) : BaseState

sealed interface TimePickerEvent : BaseEvent {
    data class OnHourSelected(val hour: Int) : TimePickerEvent

    data class OnMinuteSelected(val minute: Int): TimePickerEvent
    object OnBackPressed : TimePickerEvent
    class OnConfirm(val idTime: Int) : TimePickerEvent
}

sealed interface TimePickerAction : BaseAction {
    object Dismiss : TimePickerAction
}
