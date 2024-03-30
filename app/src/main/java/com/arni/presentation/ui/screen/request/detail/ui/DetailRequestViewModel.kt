package com.arni.presentation.ui.screen.request.detail.ui

import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetExecutorUseCase
import com.arni.domain.usecase.selects.GetPatientStatusUseCase
import com.arni.domain.usecase.selects.GetSelectStatusRequestUseCase
import com.arni.domain.usecase.selects.GetSelectUrgentlyUseCase
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.model.human.UrgencyHuman
import com.arni.presentation.model.human.UserHuman
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestViewModel
import com.arni.presentation.ui.screen.request.create.ui.PickFileOption
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class DetailRequestViewModel(
    item: RequestHuman,
    private val getSelectStatusRequestUseCase: GetSelectStatusRequestUseCase,
    private val getPatientStatusUseCase: GetPatientStatusUseCase,
    private val getSelectUrgentlyUseCase: GetSelectUrgentlyUseCase,
    private val getExecutorUseCase: GetExecutorUseCase
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
            DetailRequestEvent.onClickSelectStatus -> action = DetailRequestAction.openRequestStatusScreen(allRequestStatus)
            DetailRequestEvent.onClickSelectsubDivision -> action = DetailRequestAction.openSubDivisionScreen
            DetailRequestEvent.onClickSelectDepartament -> action = DetailRequestAction.openDepartamentScreen(listOf())
            DetailRequestEvent.onClickSelectUrgently -> action = DetailRequestAction.openUrgentlyScreen(allUrgently)
            DetailRequestEvent.onClickSelectExecutor -> action = DetailRequestAction.openExecutorScreen(allExecutor)
            DetailRequestEvent.onClickSelectStatusPatient -> action = DetailRequestAction.openStatusPatientScreen(allStatusPatient)
            is DetailRequestEvent.ChangeFilePickerOption -> changePickFileOption(act.option)
            is DetailRequestEvent.OnFileChosen -> addFiles(act.list)
            is DetailRequestEvent.OnFileDelete -> deleteFiles(act.index)
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
    private fun changePickFileOption(option: PickFileOption) {
        viewModelScope.launch {
            viewState = viewState.copy(
                currentPickFileOption = option
            )
        }
    }

    private fun addFiles(files: List<String>) {
        viewModelScope.launch {
          /*  viewState = viewState.copy(
                item = viewState.item.copy(
                    photos = viewState.item.photos.toMutableList().apply { addAll(files) }.toList()
                )
            )*/
            checkEnabledButton()
        }
    }

    private fun deleteFiles(index: Int) {
        viewModelScope.launch {
            /*viewState = viewState.copy(
                item = viewState.item.copy(
                    photos = viewState.item.photos.toMutableList().apply { removeAt(index) }.toList()
                )
            )*/
        }
    }

    private fun checkEnabledButton() {
        viewState =
            viewState.copy(
                isEnabledButton = viewState.item.statusRequest != null
                        && viewState.item.date != null
                        //todo уточнить откуда будет приходить подразделение
                        && viewState.human.subdivision != null
                        && viewState.item.departamentFrom != null
                        && viewState.item.departamentTo != null
                        && viewState.item.urgency != null
                        && viewState.item.patients != null
                        && viewState.item.statusPatient != null
            )
    }
    private val allRequestStatus: MutableList<RequestStatusHuman> = mutableListOf()
    private val allStatusPatient: MutableList<StatusPatientHuman> = mutableListOf()
    private val allUrgently: MutableList<UrgencyHuman> = mutableListOf()
    private val allExecutor: MutableList<UserHuman> = mutableListOf()
    fun getRequestStatus() {
        viewModelScope.launch {
            getSelectStatusRequestUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }

                    is DataStatus.Success -> {
                        allRequestStatus.clear()
                        allRequestStatus.addAll(it.data)
                    }
                }
            }
        }
    }

    fun getPatientStatus() {
        viewModelScope.launch {
            getPatientStatusUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }

                    is DataStatus.Success -> {
                        allStatusPatient.clear()
                        allStatusPatient.addAll(it.data)
                    }
                }
            }
        }
    }

    fun getUrgently() {
        viewModelScope.launch {
            getSelectUrgentlyUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }

                    is DataStatus.Success -> {
                        allUrgently.clear()
                        allUrgently.addAll(it.data)
                    }
                }
            }
        }
    }


    fun getExecutor() {
        viewModelScope.launch {
            getExecutorUseCase.invoke().collectLatest {
                when (it) {
                    DataStatus.Loading -> {}

                    is DataStatus.Error -> {
                        showErrorToast(it.ex)
                    }

                    is DataStatus.Success -> {
                        allExecutor.clear()
                        allExecutor.addAll(it.data)
                    }
                }
            }
        }
    }

    init {
        subscribeEvent<EventType.OnStatusRequest> {
            viewState = viewState.copy(item = viewState.item.copy(statusRequest = it.statusRequest))
        }
        getRequestStatus()

        subscribeEvent<EventType.OnStatusPatient> {
            viewState = viewState.copy(item = viewState.item.copy(statusPatient = it.statusPatient))
        }
        getPatientStatus()
        subscribeEvent<EventType.OnUrgently> {
            viewState = viewState.copy(item = viewState.item.copy(urgency = it.urgently))
        }
        getUrgently()
        subscribeEvent<EventType.OnExecutor> {
            viewState = viewState.copy(item = viewState.item.copy(executors = it.executor))
        }
        getExecutor()
    }
}
