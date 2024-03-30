package com.arni.presentation.ui.screen.request.create.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.model.human.PatientHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.model.human.SubdivisionHuman
import com.arni.presentation.model.human.UrgencyHuman
import com.arni.presentation.model.human.UserHuman
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class CreateRequestState(
    val isEnabledButton: Boolean = true,
    val item: RequestHuman = RequestHuman.getDefault(),
    val human: UserHuman = UserHuman.getDefault(),
    val subdivisionHuman: SubdivisionHuman = SubdivisionHuman.getDefault(),
    val currentPickFileOption: PickFileOption = PickFileOption.NONE,
) : BaseState
enum class PickFileOption {
    CAMERA, GALLERY, REMOVE_AVATAR, NONE
}

sealed interface CreateRequestEvent : BaseEvent {
    class OnFileChosen(val list: List<String>) : CreateRequestEvent
    class ChangeFilePickerOption(val option: PickFileOption) : CreateRequestEvent
    class OnFileDelete(val index: Int) : CreateRequestEvent

    object onClickBack : CreateRequestEvent
    object onClickSelectStatus : CreateRequestEvent
    object onClickSelectsubDivision : CreateRequestEvent
     class onClickSelectDepartament(val listDepartamentHuman: List<DepartmentHuman>) : CreateRequestEvent
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
    class openDepartamentScreen(val listDepartamentHuman: List<DepartmentHuman>): CreateRequestAction
    class openUrgentlyScreen(val list: List<UrgencyHuman>): CreateRequestAction
    class openExecutorScreen(val list: List<UserHuman>): CreateRequestAction
    class openStatusPatientScreen(val list: List<StatusPatientHuman>): CreateRequestAction
}
