package com.arni.presentation.ui.screen.pickers.yearmonthday.ui

import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


data class YearMonthDayPickerState(
    val daysList: ImmutableList<Int> = persistentListOf(),
    val monthList: ImmutableList<Int> = persistentListOf(),
    val yearList: ImmutableList<Int> = persistentListOf()
) : BaseState

sealed interface YearMonthDayPickerEvent : BaseEvent {
    object OnCreate : YearMonthDayPickerEvent

    data class OnDaySelected(val day: Int) : YearMonthDayPickerEvent

    data class OnMonthSelected(val month: Int) : YearMonthDayPickerEvent

    data class OnYearSelected(val year: Int) : YearMonthDayPickerEvent

    object OnBackPressed : YearMonthDayPickerEvent
    object OnConfirm : YearMonthDayPickerEvent
}

sealed interface YearMonthDayPickerAction : BaseAction {
    object Dismiss : YearMonthDayPickerAction
}
