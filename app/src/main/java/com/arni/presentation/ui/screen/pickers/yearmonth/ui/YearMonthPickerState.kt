package com.arni.presentation.ui.screen.pickers.yearmonth.ui

import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class YearMonthPickerState(
    val monthList: ImmutableList<Int> = persistentListOf(),
    val yearList: ImmutableList<Int> = persistentListOf()
) : BaseState

sealed interface YearMonthPickerEvent : BaseEvent {
    data class OnMonthSelected(val month: Int) : YearMonthPickerEvent
    data class OnYearSelected(val year: Int) : YearMonthPickerEvent
    object OnBackPressed : YearMonthPickerEvent
    object OnConfirm : YearMonthPickerEvent
    object OnCreate : YearMonthPickerEvent
}

sealed interface YearMonthPickerAction : BaseAction {
    object Dismiss : YearMonthPickerAction
}
