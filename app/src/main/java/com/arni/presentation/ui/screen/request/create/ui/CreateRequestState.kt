package com.arni.presentation.ui.screen.request.create.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.UserHuman
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class CreateRequestState(
    val detail: String,
    val isEnabledButton: Boolean = true,
    val item: RequestHuman = RequestHuman.getDefault(),
    val human: UserHuman = UserHuman.getDefault(),
) : BaseState

sealed interface CreateRequestEvent : BaseEvent {

    object onClickBack : CreateRequestEvent
    object onClickSelectStatus : CreateRequestEvent
    object onClickSelectsubDivision : CreateRequestEvent
    object onClickSelectDepartament : CreateRequestEvent
    object onClickSelectUrgently : CreateRequestEvent
    object onClickSelectExecutor : CreateRequestEvent
    object onClickSelectStatusPatient : CreateRequestEvent
    object onClickSelectorTime : CreateRequestEvent
    object onClickSelectorDate : CreateRequestEvent
}

sealed interface CreateRequestAction : BaseAction {
    object returnGeneralScreen : CreateRequestAction
    data class onOpenTimePickerDate(
        private val initial: LocalDate,
        private val min: LocalDate?,
        private val max: LocalDate?
    ) : CreateRequestAction

    class OpenTimePicker(
        val id: Int,
        val initial: LocalTime,
        val isToday: Boolean
    ) : CreateRequestAction

    class OpenYearMonthDayPicker(
        val id: Int,
        val initial: LocalDate,
    ) : CreateRequestAction

    object openRequestStatusScreen: CreateRequestAction
    object openSubDivisionScreen: CreateRequestAction
    object openDepartamentScreen: CreateRequestAction
    object openUrgentlyScreen: CreateRequestAction
    object openExecutorScreen: CreateRequestAction
    object openStatusPatientScreen: CreateRequestAction
}
