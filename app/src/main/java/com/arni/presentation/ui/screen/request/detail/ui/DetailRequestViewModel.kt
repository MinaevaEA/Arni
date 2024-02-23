package com.arni.presentation.ui.screen.request.detail.ui

import androidx.lifecycle.viewModelScope
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestAction
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestEvent
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class DetailRequestViewModel(
    item: RequestHuman
) : BaseViewModel<DetailRequestState, DetailRequestEvent, DetailRequestAction>(
    DetailRequestState(item = item)
) {
    override fun obtainEvent(event: DetailRequestEvent) {
        when (val act = event) {
            is DetailRequestEvent.onClickBackList -> action = DetailRequestAction.returnScreenList
            is DetailRequestEvent.onClickToolbarButton -> openEnabledRequest(act.enabled)
            DetailRequestEvent.onClickSelectorTime -> action = DetailRequestAction.OpenTimePicker(
                CreateRequestViewModel.ACT_TIME_ID,
                LocalTime.now(), true)
            DetailRequestEvent.onClickSelectorDate -> action = DetailRequestAction.OpenYearMonthDayPicker(
                CreateRequestViewModel.ACT_DATE_ID, LocalDate.now())
            DetailRequestEvent.onClickSelectStatus -> action = DetailRequestAction.openRequestStatusScreen
            DetailRequestEvent.onClickSelectsubDivision -> action = DetailRequestAction.openSubDivisionScreen
            DetailRequestEvent.onClickSelectDepartament -> action = DetailRequestAction.openDepartamentScreen
            DetailRequestEvent.onClickSelectUrgently -> action = DetailRequestAction.openUrgentlyScreen
            DetailRequestEvent.onClickSelectExecutor -> action = DetailRequestAction.openExecutorScreen
            DetailRequestEvent.onClickSelectStatusPatient -> action = DetailRequestAction.openStatusPatientScreen
        }
    }
    fun openEnabledRequest(enabled: Boolean){
        viewState = viewState.copy(enabled = enabled)
        checkEnabledButton()
    }

    init {
        viewModelScope.launch {
            viewState = viewState.copy(item = item)
        }
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
