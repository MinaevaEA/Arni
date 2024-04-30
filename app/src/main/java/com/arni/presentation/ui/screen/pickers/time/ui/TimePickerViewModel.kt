package com.arni.presentation.ui.screen.pickers.time.ui

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import java.time.LocalTime


@SuppressLint("NewApi")
class TimePickerViewModel(
    private val initial: LocalTime = LocalTime.now(),
    private val min: LocalTime? = LocalTime.now(),
    private val max: LocalTime? = LocalTime.now(),
) : BaseViewModel<TimePickerState, TimePickerEvent, TimePickerAction>(
    TimePickerState()
) {

    private var selectedHour = initial.hour
    private var selectedMinute = initial.minute

    init {
        initSpinner()
    }

    override fun obtainEvent(event: TimePickerEvent) {
        when (event) {
            is TimePickerEvent.OnHourSelected -> changeHour(event.hour)
            is TimePickerEvent.OnMinuteSelected -> changeMinute(event.minute)
            TimePickerEvent.OnBackPressed -> action = TimePickerAction.Dismiss
            is TimePickerEvent.OnConfirm -> confirm(event.idTime)
            else -> {}
        }
    }

    private fun initSpinner() {
        val minHour = if (min?.hour != null) min.hour else 0
        val maxHour = max?.hour ?: 23
        val initialHour = initial.hour
        val firstHoursRange = initialHour..maxHour
        val secondHoursRange = minHour until initialHour
        val hoursList = (firstHoursRange).toList() + (secondHoursRange).toList()

        viewState = viewState.copy(
            hoursList = hoursList.toPersistentList(),
            minutesList = generateMinutesForHour(initialHour).toPersistentList()
        )
    }

    private fun changeHour(newHour: Int) {
        selectedHour = newHour
        viewState = viewState.copy(minutesList = generateMinutesForHour(newHour).toPersistentList())
    }

    private fun generateMinutesForHour(hour: Int): List<Int> {
        val minHour = if (min?.hour != null) min.hour else 0
        val maxHour = max?.hour ?: 23

        val minMinute = if (min?.minute != null) min.minute else 0
        val maxMinute = max?.minute ?: 59
        val initialMinute = initial.minute
        val firstMinutesRange = if (hour == maxHour)
            initialMinute..maxMinute
        else
            initialMinute..59

        val secondMinutesRange = if (hour == minHour)
            minMinute until initialMinute
        else
            0 until initialMinute

        return (firstMinutesRange).toList() + (secondMinutesRange).toList()
    }

    private fun changeMinute(newMinute: Int) {
        selectedMinute = newMinute
    }

    private fun confirm(idTime: Int) {
        viewModelScope.launch {
            when (idTime) {
                1 -> publishEvent(EventType.OnTimeRequestPicked(LocalTime.of(selectedHour, selectedMinute, 1)))
                2 -> publishEvent(EventType.OnTimeBeginPicked(LocalTime.of(selectedHour, selectedMinute, 1)))
                3 -> publishEvent(EventType.OnTimeEndPicked(LocalTime.of(selectedHour, selectedMinute, 1)))
            }
        }
        action = TimePickerAction.Dismiss
    }

}

