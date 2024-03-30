package com.arni.presentation.ui.screen.request.create.ui

import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects.GetExecutorUseCase
import com.arni.domain.usecase.selects.GetPatientStatusUseCase
import com.arni.domain.usecase.selects.GetSelectStatusRequestUseCase
import com.arni.domain.usecase.selects.GetSelectUrgentlyUseCase
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.PatientHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.model.human.UrgencyHuman
import com.arni.presentation.model.human.UserHuman
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class CreateRequestViewModel(
    private val getSelectStatusRequestUseCase: GetSelectStatusRequestUseCase,
    private val getPatientStatusUseCase: GetPatientStatusUseCase,
    private val getSelectUrgentlyUseCase: GetSelectUrgentlyUseCase,
    private val getExecutorUseCase: GetExecutorUseCase
) : BaseViewModel<CreateRequestState, CreateRequestEvent, CreateRequestAction>(
    CreateRequestState()
) {
    private val allRequestStatus: MutableList<RequestStatusHuman> = mutableListOf()
    private val allStatusPatient: MutableList<StatusPatientHuman> = mutableListOf()
    private val allUrgently: MutableList<UrgencyHuman> = mutableListOf()
    private val allExecutor: MutableList<UserHuman> = mutableListOf()
    override fun obtainEvent(event: CreateRequestEvent) {
        when (event) {
            is CreateRequestEvent.onClickBack -> action = CreateRequestAction.returnGeneralScreen
            CreateRequestEvent.onClickSelectorTime -> action = CreateRequestAction.OpenTimePicker(
                ACT_TIME_ID,
                LocalTime.now(), true
            )

            CreateRequestEvent.onClickSelectorDate -> action =
                CreateRequestAction.OpenYearMonthDayPicker(ACT_DATE_ID, LocalDate.now())

            CreateRequestEvent.onClickSelectStatus -> action =
                CreateRequestAction.openRequestStatusScreen(allRequestStatus)

            CreateRequestEvent.onClickSelectsubDivision -> action = CreateRequestAction.openSubDivisionScreen
            is CreateRequestEvent.onClickSelectDepartament -> action =
                CreateRequestAction.openDepartamentScreen(event.listDepartamentHuman)

            CreateRequestEvent.onClickSelectUrgently -> action = CreateRequestAction.openUrgentlyScreen(allUrgently)
            CreateRequestEvent.onClickSelectExecutor -> action = CreateRequestAction.openExecutorScreen(allExecutor)
            CreateRequestEvent.onClickSelectStatusPatient -> action =
                CreateRequestAction.openStatusPatientScreen(allStatusPatient)

            is CreateRequestEvent.ChangeFilePickerOption -> changePickFileOption(event.option)
            is CreateRequestEvent.OnFileChosen -> addFiles(event.list)
            is CreateRequestEvent.OnFileDelete -> deleteFiles(event.index)
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

    companion object {
        const val ACT_TIME_ID = 1
        const val ACT_DATE_ID = 2
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
            viewState = viewState.copy(
                item = viewState.item.copy(
                   // photos = viewState.item.photos.toMutableList().apply { addAll(files) }.toList()
                )
            )
            checkEnabledButton()
        }
    }

    private fun deleteFiles(index: Int) {
        viewModelScope.launch {
            viewState = viewState.copy(
                item = viewState.item.copy(
                   // photos = viewState.item.photos.toMutableList().apply { removeAt(index) }.toList()
                )
            )
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
}
