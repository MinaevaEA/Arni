package com.arni.presentation.ui.screen.pickers.yearmonth.ui

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

@SuppressLint("NewApi")
class YearMonthPickerViewModel(
    private val initial: YearMonth,
    private val min: YearMonth?,
    private val max: YearMonth?
) : BaseViewModel<YearMonthPickerState, YearMonthPickerEvent, YearMonthPickerAction>(
    YearMonthPickerState()
) {

    private var selectedYear = initial.year
    private var selectedMonth = initial.month.value

    override fun obtainEvent(event: YearMonthPickerEvent) {
        when (event) {
            is YearMonthPickerEvent.OnCreate -> initSpinner()

            is YearMonthPickerEvent.OnMonthSelected -> changeMonth(event.month)

            is YearMonthPickerEvent.OnYearSelected -> changeYear(event.year)

            is YearMonthPickerEvent.OnBackPressed -> action = YearMonthPickerAction.Dismiss

            is YearMonthPickerEvent.OnConfirm -> confirm()

        }
    }

    private fun initSpinner() {
        val minYear = min?.year ?: MIN_YEAR
        val maxYear = max?.year ?: MAX_YEAR
        val initialYear = initial.year
        val firstYearRange = initialYear..maxYear
        val secondYearRange = minYear until initialYear
        val yearList = (firstYearRange.toList()) + (secondYearRange.toList())

        viewState = viewState.copy(
            yearList = yearList.toPersistentList(),
            monthList = generateMonthForYear(initialYear).toPersistentList()
        )

    }

    private fun generateMonthForYear(year: Int): List<Int> {
        val minYear = min?.year ?: LocalDate.MIN.year
        val maxYear = max?.year ?: LocalDate.MAX.year

        val minMonth = min?.month?.value ?: 1
        val maxMonth = max?.month?.value ?: 12

        val initialMonth =
            if (year == minYear && initial.month.value < minMonth) minMonth else initial.month.value

        val firstMonthRange = if (year == maxYear)
            initialMonth..maxMonth
        else initialMonth..12

        val secondMonthRange = if (year == minYear)
            minMonth until initialMonth
        else 1 until initialMonth

        return firstMonthRange.toList() + secondMonthRange.toList()
    }

    private fun changeMonth(newMonth: Int) {
        selectedMonth = newMonth
    }

    private fun changeYear(newYear: Int) {
        selectedYear = newYear
        viewState = viewState.copy(
            monthList = generateMonthForYear(newYear).toPersistentList()
        )
    }

    private fun confirm() {
        viewModelScope.launch {
            publishEvent(
                EventType.OnYearMonthPicked(
                    YearMonth.of(selectedYear, selectedMonth)
                )
            ) // написал 1 число
        }
        action = YearMonthPickerAction.Dismiss
    }


    companion object {
        private const val MIN_YEAR = 1900
        private val MAX_YEAR = YearMonth.now().plusYears(10).year
    }

}
