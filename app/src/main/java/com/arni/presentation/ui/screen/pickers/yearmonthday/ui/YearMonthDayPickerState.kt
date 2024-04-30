package com.arni.presentation.ui.screen.pickers.yearmonthday.ui

import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import java.time.LocalDate

//sealed interface YearMonthDayPickerState : BaseState

//object Empty : YearMonthDayPickerState

data class YearMonthDayPickerState(
    val daysList: List<Int> = listOf(),
    val monthList: List<Int> = listOf(),
    val yearList: List<Int> = listOf(),
    val idDate: Int = 0
) : BaseState

sealed interface YearMonthDayPickerEvent : BaseEvent {
    object OnCreate : YearMonthDayPickerEvent

    data class OnDaySelected(val day: Int) : YearMonthDayPickerEvent

    data class OnMonthSelected(val month: Int) : YearMonthDayPickerEvent

    data class OnYearSelected(val year: Int) : YearMonthDayPickerEvent

    object OnBackPressed : YearMonthDayPickerEvent
    class OnConfirm(val idDate: Int) : YearMonthDayPickerEvent
}

sealed interface YearMonthDayPickerAction : BaseAction {
    object Dismiss : YearMonthDayPickerAction
}

