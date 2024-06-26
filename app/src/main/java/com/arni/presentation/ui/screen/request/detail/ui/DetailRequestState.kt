package com.arni.presentation.ui.screen.request.detail.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DispatcherHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.ExecutorHuman
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
    val dictionary: DictionaryHuman = DictionaryHuman.getDefault(),
    val divisionHuman: DivisionHuman = DivisionHuman.getDefault(),
    val enabled: Boolean = false,
    val isEditRequest: Boolean = true,
    val listId: String,
    val isEnabledButton: Boolean = true,
    val currentPickFileOption: PickFileOption = PickFileOption.NONE,
) : BaseState /*{
        override fun setEndDate(endDate: LocalDate): DetailRequestState {
            return this.copy(
                item = item.copy(
                    endDate = endDate.toString(),
                    startDate = item.startDate
                )
            )

        }

        override fun setStartDate(startDate: LocalDate): DetailRequestState {
            return this.copy(
                item = item.copy(
                    startDate = startDate.toString(),
                    endDate = item.endDate
                )
            )
        }
    }
}*/

sealed interface DetailRequestEvent : BaseEvent {
    class onChangeDescription(val text: String) : DetailRequestEvent
    class isDelete(val isDelete: Boolean) : DetailRequestEvent
    class OnFileChosen(val list: List<String>) : DetailRequestEvent
    class ChangeFilePickerOption(val option: PickFileOption) : DetailRequestEvent
    class OnFileDelete(val index: Int) : DetailRequestEvent
    object onClickBackList : DetailRequestEvent
    class onClickToolbarButton(val item: RequestHuman, val enabled: Boolean) : DetailRequestEvent
    class onClickSelectStatus(val listStatus: List<RequestStatusHuman>) : DetailRequestEvent
    class onClickSelectDivision(val listDivision: List<DivisionHuman>) : DetailRequestEvent
    class onClickSelectDepartamentFrom(val listDepartmentHuman: List<DepartmentHuman>) : DetailRequestEvent
    class onClickSelectDepartamentTo(val listDepartmentHuman: List<DepartmentHuman>) : DetailRequestEvent
    class OnDepartmentFrom(val newDepartmentHuman: DepartmentHuman) : DetailRequestEvent
    class OnDepartmentTo(val newDepartmentHuman: DepartmentHuman) : DetailRequestEvent
    class onChangePatient(val text: String) : DetailRequestEvent
    class onChangeDataRequest(val text: String) : DetailRequestEvent
    class onClickSelectUrgently(val listUrgencyHuman: List<UrgencyHuman>) : DetailRequestEvent
    class onClickSelectDispatchers(val listDispatcherHuman: List<DispatcherHuman>) : DetailRequestEvent
    class onClickSelectExecutor(val listExecutor: List<ExecutorHuman>) : DetailRequestEvent
    class onClickSelectStatusPatient(val listStatusPatient: List<StatusPatientHuman>) : DetailRequestEvent
    object onClickSelectorTimeRequest : DetailRequestEvent
    object onClickSelectorDateRequest : DetailRequestEvent

    object onClickSelectorTimeBegin : DetailRequestEvent
    object onClickSelectorDateBegin : DetailRequestEvent
    object onClickSelectorTimeEnd : DetailRequestEvent
    object onClickSelectorDateEnd : DetailRequestEvent


}

sealed interface DetailRequestAction : BaseAction {
    object returnScreenList : DetailRequestAction
    class onChangeRequestisDelete(val isDelete: Boolean) : DetailRequestAction

    object isCheck : DetailRequestAction
    class openRequestStatusScreen(val list: List<RequestStatusHuman>) : DetailRequestAction
    class openDivisionScreen(val listSubDivision: List<DivisionHuman>) : DetailRequestAction
    class openDepartamentScreenFrom(val listDepartmentHuman: List<DepartmentHuman>) : DetailRequestAction
    class openDepartamentScreenTo(val listDepartmentHuman: List<DepartmentHuman>) : DetailRequestAction
    class openUrgentlyScreen(val list: List<UrgencyHuman>) : DetailRequestAction
    class openDispatcherScreen(val list: List<DispatcherHuman>) : DetailRequestAction
    class openExecutorScreen(val list: List<ExecutorHuman>) : DetailRequestAction
    class openStatusPatientScreen(val list: List<StatusPatientHuman>) : DetailRequestAction
    class OpenTimePickerRequest(
        val initial: LocalTime,
        val minDate: LocalTime,
        val maxDate: LocalTime,
        val id: Int
    ) : DetailRequestAction


    class OpenYearMonthDayPickerRequest(
        val selectDate: LocalDate,
        val minDate: LocalDate,
        val maxDate: LocalDate,
        val id: Int
    ) : DetailRequestAction

    class OpenTimePickerStart(
        val initial: LocalTime,
        val minDate: LocalTime,
        val maxDate: LocalTime,
        val id: Int
    ) : DetailRequestAction


    class OpenYearMonthDayPickerStart(
        val selectDate: LocalDate,
        val minDate: LocalDate,
        val maxDate: LocalDate,
        val id: Int
    ) : DetailRequestAction

    class OpenTimePickerEnd(
        val initial: LocalTime,
        val minDate: LocalTime,
        val maxDate: LocalTime,
        val id: Int
    ) : DetailRequestAction


    class OpenYearMonthDayPickerEnd(
        val selectDate: LocalDate,
        val minDate: LocalDate,
        val maxDate: LocalDate,
        val id: Int
    ) : DetailRequestAction
}
