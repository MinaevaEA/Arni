package com.arni.presentation.ui.screen.request.detail.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.model.human.UrgencyHuman
import com.arni.presentation.model.human.UserHuman
import com.arni.presentation.ui.screen.request.create.ui.PickFileOption
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class DetailRequestState(
    val item: RequestHuman = RequestHuman.getDefault(),
    val human: UserHuman = UserHuman.getDefault(),
    val enabled: Boolean = false,
    val isEditRequest: Boolean = true,
    val isEnabledButton: Boolean = true,
    val currentPickFileOption: PickFileOption = PickFileOption.NONE,
) : BaseState

sealed interface DetailRequestEvent : BaseEvent {
    class OnFileChosen(val list: List<String>) : DetailRequestEvent
    class ChangeFilePickerOption(val option: PickFileOption) : DetailRequestEvent
    class OnFileDelete(val index: Int) : DetailRequestEvent
    object onClickBackList : DetailRequestEvent
    class onClickToolbarButton(val enabled: Boolean) : DetailRequestEvent
    object onClickSelectStatus : DetailRequestEvent
    object onClickSelectsubDivision : DetailRequestEvent
    object onClickSelectDepartament : DetailRequestEvent
    object onClickSelectUrgently : DetailRequestEvent
    object onClickSelectExecutor : DetailRequestEvent
    object onClickSelectStatusPatient : DetailRequestEvent
    object onClickSelectorTime : DetailRequestEvent
    object onClickSelectorDate : DetailRequestEvent


}

sealed interface DetailRequestAction : BaseAction {
    object returnScreenList : DetailRequestAction

    class openRequestStatusScreen(val list: List<RequestStatusHuman>): DetailRequestAction
    object openSubDivisionScreen: DetailRequestAction
    class openDepartamentScreen(val listDepartamentHuman: List<DepartmentHuman>): DetailRequestAction
    class openUrgentlyScreen(val list: List<UrgencyHuman>): DetailRequestAction
    class openExecutorScreen(val list: List<UserHuman>): DetailRequestAction
    class openStatusPatientScreen(val list: List<StatusPatientHuman>): DetailRequestAction
    class OpenTimePicker(
        val id: Int,
        val initial: LocalTime,
        val isToday: Boolean
    ) : DetailRequestAction

    class OpenYearMonthDayPicker(
        val id: Int,
        val initial: LocalDate,
    ) : DetailRequestAction

}
