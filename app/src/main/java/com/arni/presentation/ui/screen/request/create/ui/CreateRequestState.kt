package com.arni.presentation.ui.screen.request.create.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class CreateRequestState(val detail: String): BaseState
sealed interface CreateRequestEvent : BaseEvent {

    object onClickBack: CreateRequestEvent
    object onClickSelectorTime: CreateRequestEvent
}
sealed interface CreateRequestAction : BaseAction {
    object returnGeneralScreen: CreateRequestAction
    data class onOpenTimePickerDate(private val initial: LocalDate,
                                    private val min: LocalDate?,
                                    private val max: LocalDate?): CreateRequestAction

    class OpenTimePicker(
        val id: Int,
        val initial: LocalTime,
        val isToday : Boolean
    ) : CreateRequestAction

    class OpenYearMonthDayPicker(
        val id: Int,
        val initial: LocalDate,
    ) : CreateRequestAction
}
