package com.arni.presentation.ui.screen.pickers.yearmonthday.ui

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import java.time.LocalDate

@SuppressLint("NewApi")
class YearMonthDayPickerViewModel(
/*    private val id: Int,
    private val initial: LocalDate,
    private val min: LocalDate?,
    private val max: LocalDate?*/
) : BaseViewModel<YearMonthDayPickerState, YearMonthDayPickerEvent, YearMonthDayPickerAction>(
    YearMonthDayPickerState()
) {

   /* private var selectedYear = initial.year
    private var selectedMonth = initial.month.value
    private var selectedDay = initial.dayOfMonth*/

    override fun obtainEvent(event: YearMonthDayPickerEvent) {
        when (event) {
      /*      is YearMonthDayPickerEvent.OnCreate -> initSpinner()
            is YearMonthDayPickerEvent.OnDaySelected -> {
                changeDay(event.day)
            }

            is YearMonthDayPickerEvent.OnMonthSelected -> {
                changeMonth(event.month)
            }

            is YearMonthDayPickerEvent.OnYearSelected -> {
                changeYear(event.year)
            }

            is YearMonthDayPickerEvent.OnBackPressed -> action = YearMonthDayPickerAction.Dismiss
            is YearMonthDayPickerEvent.OnConfirm -> confirm()*/
            else -> {}
        }
    }

/*    private fun initSpinner() {
        val minYear = min?.year ?: MIN_YEAR
        val maxYear = max?.year ?: MAX_YEAR
        val initialYear = initial.year
        val initialMonth = initial.month.value
        val firstYearRange = initialYear..maxYear
        val secondYearRange = minYear until initialYear
        val yearList = (firstYearRange.toList()) + (secondYearRange.toList())

        viewState = viewState.copy(
            yearList = yearList.toPersistentList(),
            monthList = generateMonthForYear(initialYear).toPersistentList(),
            daysList = generateDayForMonthYear(initialYear, initialMonth).toPersistentList()
        )
    }

    private fun generateMonthForYear(year: Int): List<Int> {
        val minYear = min?.year ?: LocalDate.MIN.year
        val maxYear = max?.year ?: LocalDate.MAX.year

        val minMonth = min?.month?.value ?: 1
        val maxMonth = max?.month?.value ?: 12
        val initialMonth = initial.month.value

        val firstMonthRange = if (year == maxYear)
            initialMonth..maxMonth
        else initialMonth..12

        val secondMonthRange = if (year == minYear)
            minMonth until initialMonth
        else 1 until initialMonth

        return firstMonthRange.toList() + secondMonthRange.toList()
    }

    private fun generateDayForMonthYear(year: Int, month: Int): List<Int> {
        val minYear = min?.year ?: MIN_YEAR
        val maxYear = max?.year ?: MAX_YEAR

        val minMonth = min?.month?.value ?: 1
        val maxMonth = max?.month?.value ?: 12

        val lengthMonth = LocalDate.of(year, month,1).lengthOfMonth()

        val minDay = min?.dayOfMonth ?: 1
        val maxDay = max?.dayOfMonth ?: lengthMonth

        val initialDay = initial.dayOfMonth

        val firstDayRange = if (year == maxYear && month == maxMonth)
            initialDay..maxDay
        else
            initialDay..lengthMonth

        val secondDayRange = if (year == minYear && month == minMonth)
            minDay until initialDay
        else
            1 until initialDay


        return firstDayRange.toList() + secondDayRange.toList()
    }

    private fun changeDay(newDay: Int) {
        selectedDay = newDay
    }

    private fun changeMonth(newMonth: Int) {
        selectedMonth = newMonth
        viewState = viewState.copy(
            daysList = generateDayForMonthYear(selectedYear, newMonth).toPersistentList()
        )
    }

    private fun changeYear(newYear: Int) {
        selectedYear = newYear
        viewState = viewState.copy(
            monthList = generateMonthForYear(newYear).toPersistentList(),
            daysList = generateDayForMonthYear(newYear, selectedMonth).toPersistentList()
        )
    }

    private fun confirm() {
        viewModelScope.launch {
            publishEvent(
                EventType.OnYearMonthDayPicked(
                    LocalDate.of(selectedYear, selectedMonth, selectedDay),
                    id
                )
            )
        }
        action = YearMonthDayPickerAction.Dismiss
    }

    companion object {

        private const val MIN_YEAR = 1900
        private val MAX_YEAR = LocalDate.now().plusYears(10).year

    }*/

}
