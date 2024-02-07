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
    private val id: Int,
    private val initial: LocalTime,
    private val min: LocalTime?,
    private val max: LocalTime?,
    private val isCropMinTime: Boolean
) : BaseViewModel<TimePickerState, TimePickerEvent, TimePickerAction>(
    TimePickerState()
) {

    private var selectedHour = initial.hour
    private var selectedMinute = initial.minute

    override fun obtainEvent(event: TimePickerEvent) {
        when (event) {
            TimePickerEvent.OnCreate -> initSpinner()
            is TimePickerEvent.OnHourSelected -> changeHour(event.hour)
            is TimePickerEvent.OnMinuteSelected -> changeMinute(event.minute)
            TimePickerEvent.OnBackPressed -> action = TimePickerAction.Dismiss
            TimePickerEvent.OnConfirm -> confirm()
        }
    }

    private fun initSpinner() {
        val minHour = if (isCropMinTime && min?.hour != null) min.hour else 0
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
        val minHour = if (isCropMinTime && min?.hour != null) min.hour else 0
        val maxHour = max?.hour ?: 23

        val minMinute = if (isCropMinTime && min?.minute != null) min.minute else 0
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

    private fun confirm() {
        val minHour = min?.hour ?: 0
        val minMinute = min?.minute ?: 0
        viewModelScope.launch {
            if (!isCropMinTime && (selectedHour < minHour || selectedHour == minHour && selectedMinute < minMinute))
               // showTextToast("Укажите нежелательное время в будущем")
            else {
                publishEvent(EventType.OnTimePicked(LocalTime.of(selectedHour, selectedMinute), id))
                action = TimePickerAction.Dismiss
            }
        }
    }

}

