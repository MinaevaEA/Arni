package com.arni.presentation.ui.screen.request.create.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DepartamentHuman
import com.arni.presentation.model.human.PatientStatusHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.SubdivisionHuman
import com.arni.presentation.model.human.UrgentlyHuman
import com.arni.presentation.model.human.UserHuman
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class CreateRequestState(
    val detail: String,
    val isEnabledButton: Boolean = true,
    val isVisibleHat: Boolean = true,
    val item: RequestHuman = RequestHuman.getDefault(),
    val human: UserHuman = UserHuman.getDefault(),
    val subdivisionHuman: SubdivisionHuman = SubdivisionHuman.getDefault()
) : BaseState

sealed interface CreateRequestEvent : BaseEvent {

    object onClickBack : CreateRequestEvent
    object onClickSelectStatus : CreateRequestEvent
    object onClickSelectsubDivision : CreateRequestEvent
     class onClickSelectDepartament(val listDepartamentHuman: List<DepartamentHuman>) : CreateRequestEvent
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

    class openRequestStatusScreen(val list: List<RequestStatusHuman>): CreateRequestAction
    object openSubDivisionScreen: CreateRequestAction
    class openDepartamentScreen(val listDepartamentHuman: List<DepartamentHuman>): CreateRequestAction
    class openUrgentlyScreen(val list: List<UrgentlyHuman>): CreateRequestAction
    class openExecutorScreen(val list: List<UserHuman>): CreateRequestAction
    class openStatusPatientScreen(val list: List<PatientStatusHuman>): CreateRequestAction
}
