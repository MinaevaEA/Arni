package com.arni.presentation.ui.screen.request.create.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.enum.StatusRequests
import com.arni.presentation.model.human.CreateRequestHuman
import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DispatcherHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.ExecutorHuman
import com.arni.presentation.model.human.PatientHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.model.human.SubdivisionHuman
import com.arni.presentation.model.human.UrgencyHuman
import com.arni.presentation.model.human.UserHuman
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestAction
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestEvent
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class CreateRequestState(
    val listId: String,
    val isEnabledButton: Boolean = true,
    val item: CreateRequestHuman = CreateRequestHuman.getDefault(),
    val dictionary: DictionaryHuman = DictionaryHuman.getDefault(),
    val divisionHuman: DivisionHuman = DivisionHuman.getDefault(),
    val currentPickFileOption: PickFileOption = PickFileOption.NONE,
) : BaseState
enum class PickFileOption {
    CAMERA, GALLERY, REMOVE_AVATAR, NONE
}

sealed interface CreateRequestEvent : BaseEvent {
    class onChangeDescription(val text: String) : CreateRequestEvent
    class onClickSelectDispatchers(val listDispatcherHuman: List<DispatcherHuman>) : CreateRequestEvent
    class OnFileChosen(val list: List<String>) : CreateRequestEvent
    class ChangeFilePickerOption(val option: PickFileOption) : CreateRequestEvent
    class OnFileDelete(val index: Int) : CreateRequestEvent
    class onChangePatient(val text: String) : CreateRequestEvent

    object onClickBack : CreateRequestEvent
    class onClickSelectStatus(val statusRequests: List<RequestStatusHuman>) : CreateRequestEvent
    class onClickSelectDivision(val listDivision: List<DivisionHuman>) : CreateRequestEvent
    class onClickSelectDepartamentFrom(val listDepartmentHuman: List<DepartmentHuman>) : CreateRequestEvent
    class onClickSelectDepartamentTo(val listDepartmentHuman: List<DepartmentHuman>) : CreateRequestEvent

    class onClickSelectUrgently(val listUrgencyHuman: List<UrgencyHuman>) : CreateRequestEvent
    class onClickSelectExecutor(val listExecutor: List<ExecutorHuman>) : CreateRequestEvent
    class onClickSelectStatusPatient(val listStatusPatient: List<StatusPatientHuman>) : CreateRequestEvent
    object onClickSelectorTimeRequest : CreateRequestEvent
    object onClickSelectorDateRequest : CreateRequestEvent

    object onClickSelectorTimeBegin : CreateRequestEvent
    object onClickSelectorDateBegin : CreateRequestEvent
    object onClickSelectorTimeEnd : CreateRequestEvent
    object onClickSelectorDateEnd : CreateRequestEvent
    class OnDepartmentFrom(val newDepartmentHuman: DepartmentHuman) : CreateRequestEvent
    class OnDepartmentTo(val newDepartmentHuman: DepartmentHuman) : CreateRequestEvent
    class onClickAddRequest(val item: CreateRequestHuman): CreateRequestEvent
}

sealed interface CreateRequestAction : BaseAction {
    object returnGeneralScreen : CreateRequestAction

    class openDispatcherScreen(val list: List<DispatcherHuman>) : CreateRequestAction
    class OpenYearMonthDayPickerRequest(
        val selectDate: LocalDate,
        val minDate: LocalDate,
        val maxDate: LocalDate,
        val id: Int
    ) : CreateRequestAction

    class OpenTimePickerRequest(
        val initial: LocalTime,
        val minDate: LocalTime,
        val maxDate: LocalTime,
        val id: Int
    ) : CreateRequestAction

    class OpenTimePickerStart(
        val initial: LocalTime,
        val minDate: LocalTime,
        val maxDate: LocalTime,
        val id: Int
    ) : CreateRequestAction


    class OpenYearMonthDayPickerStart(
        val selectDate: LocalDate,
        val minDate: LocalDate,
        val maxDate: LocalDate,
        val id: Int
    ) : CreateRequestAction

    class OpenTimePickerEnd(
        val initial: LocalTime,
        val minDate: LocalTime,
        val maxDate: LocalTime,
        val id: Int
    ) : CreateRequestAction


    class OpenYearMonthDayPickerEnd(
        val selectDate: LocalDate,
        val minDate: LocalDate,
        val maxDate: LocalDate,
        val id: Int
    ) : CreateRequestAction

    class openRequestStatusScreen(val list: List<RequestStatusHuman>): CreateRequestAction
    data class openDivisionScreen(val listDivision: List<DivisionHuman>): CreateRequestAction
    class openDepartamentScreenFrom(val listDepartmentHuman: List<DepartmentHuman>) : CreateRequestAction
    class openDepartamentScreenTo(val listDepartmentHuman: List<DepartmentHuman>) : CreateRequestAction
    class openUrgentlyScreen(val list: List<UrgencyHuman>): CreateRequestAction
    class openExecutorScreen(val list: List<ExecutorHuman>): CreateRequestAction
    class openStatusPatientScreen(val list: List<StatusPatientHuman>): CreateRequestAction
}
