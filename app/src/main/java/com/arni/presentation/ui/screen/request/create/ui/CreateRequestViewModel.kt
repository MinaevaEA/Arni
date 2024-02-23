package com.arni.presentation.ui.screen.request.create.ui

import com.arni.presentation.base.BaseViewModel
import java.time.LocalDate
import java.time.LocalTime

class CreateRequestViewModel(
) : BaseViewModel<CreateRequestState, CreateRequestEvent, CreateRequestAction>(
    CreateRequestState(detail = "")
) {
    override fun obtainEvent(event: CreateRequestEvent) {
        when (event) {
            is CreateRequestEvent.onClickBack -> action = CreateRequestAction.returnGeneralScreen
            CreateRequestEvent.onClickSelectorTime -> action = CreateRequestAction.OpenTimePicker(ACT_TIME_ID,
                LocalTime.now(), true)
            CreateRequestEvent.onClickSelectorDate -> action = CreateRequestAction.OpenYearMonthDayPicker(ACT_DATE_ID, LocalDate.now())
            CreateRequestEvent.onClickSelectStatus -> action = CreateRequestAction.openRequestStatusScreen
            CreateRequestEvent.onClickSelectsubDivision -> action = CreateRequestAction.openSubDivisionScreen
            CreateRequestEvent.onClickSelectDepartament -> action = CreateRequestAction.openDepartamentScreen
            CreateRequestEvent.onClickSelectUrgently -> action = CreateRequestAction.openUrgentlyScreen
            CreateRequestEvent.onClickSelectExecutor -> action = CreateRequestAction.openExecutorScreen
            CreateRequestEvent.onClickSelectStatusPatient -> action = CreateRequestAction.openStatusPatientScreen
        }
    }
    companion object {
        const val ACT_TIME_ID = 1
        const val ACT_DATE_ID = 2
    }
    private fun checkEnabledButton() {
        viewState =
            viewState.copy(
                isEnabledButton = viewState.item.statusRequest != null
                        && viewState.item.date != null
                        //todo уточнить откуда будет приходить подразделение
                        && viewState.human.subdivision != null
                        && viewState.item.fromDepartament != null
                        && viewState.item.toDepartament != null
                        && viewState.item.urgency != null
                        && !viewState.item.namePatient.isNullOrBlank() != null
                        && viewState.item.statusPatient != null
            )
    }
}
