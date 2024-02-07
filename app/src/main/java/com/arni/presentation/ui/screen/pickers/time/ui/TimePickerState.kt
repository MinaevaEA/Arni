package com.arni.presentation.ui.screen.pickers.time.ui

import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


data class TimePickerState(
    val hoursList: ImmutableList<Int> = persistentListOf(),
    val minutesList: ImmutableList<Int> = persistentListOf(),
) : BaseState

sealed interface TimePickerEvent : BaseEvent {
    object OnCreate: TimePickerEvent
    data class OnHourSelected(val hour: Int) : TimePickerEvent

    data class OnMinuteSelected(val minute: Int): TimePickerEvent
    object OnBackPressed : TimePickerEvent
    object OnConfirm : TimePickerEvent
}

sealed interface TimePickerAction : BaseAction {
    object Dismiss : TimePickerAction
}
