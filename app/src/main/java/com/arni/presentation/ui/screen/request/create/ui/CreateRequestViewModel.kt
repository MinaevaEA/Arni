package com.arni.presentation.ui.screen.request.create.ui

import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.selects_delete.GetExecutorUseCase
import com.arni.domain.usecase.selects_delete.GetPatientStatusUseCase
import com.arni.domain.usecase.selects_delete.GetSelectStatusRequestUseCase
import com.arni.domain.usecase.selects_delete.GetSelectUrgentlyUseCase
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.CreateRequestHuman
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.PatientHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.model.human.UrgencyHuman
import com.arni.presentation.model.human.UserHuman
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestAction
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class CreateRequestViewModel(
    val listId: String,
    val dictionary: DictionaryHuman,
    val divisionHuman: DivisionHuman
) : BaseViewModel<CreateRequestState, CreateRequestEvent, CreateRequestAction>(
    CreateRequestState(dictionary = dictionary, listId = listId, divisionHuman = divisionHuman)
) {

    var dateCreateRequestCurrent = LocalDate.now()
    var dateBeginCurrent = LocalDate.now()
    var dateEndCurrent = LocalDate.now()
    var timeRequestCurrent = LocalTime.of(0, 0, 1)
    var timeBeginCurrent = LocalTime.of(0, 0, 1)
    var timeEndCurrent = LocalTime.of(0, 0, 1)
    private val allRequestStatus: MutableList<RequestStatusHuman> = mutableListOf()
    private val allStatusPatient: MutableList<StatusPatientHuman> = mutableListOf()
    override fun obtainEvent(event: CreateRequestEvent) {
        when (event) {
            is CreateRequestEvent.onClickBack -> action = CreateRequestAction.returnGeneralScreen
            CreateRequestEvent.onClickSelectorTime -> action = CreateRequestAction.OpenTimePickerRequest(
                LocalTime.parse(viewState.item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                minDate = LocalTime.of(0, 0, 0),
                maxDate = LocalTime.of(23, 59, 59),
                1
            )

            CreateRequestEvent.onClickSelectorDate -> action = CreateRequestAction.OpenYearMonthDayPickerRequest(
                LocalDate.parse(viewState.item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                minDate = LocalDate.of(2010, 12, 31),
                maxDate = LocalDate.of(2080, 12, 31),
                1
            )

            is CreateRequestEvent.onChangeDescription -> changeComment(event.text)
            is CreateRequestEvent.onClickSelectStatusPatient ->
                action = CreateRequestAction.openStatusPatientScreen(event.listStatusPatient)

            CreateRequestEvent.onClickSelectStatus -> action =
                CreateRequestAction.openRequestStatusScreen(allRequestStatus)

            is CreateRequestEvent.onClickSelectDivision ->
                action = CreateRequestAction.openDivisionScreen(event.listDivision)


            is CreateRequestEvent.onClickSelectDepartamentFrom ->
                action = CreateRequestAction.openDepartamentScreenFrom(event.listDepartmentHuman)

            is CreateRequestEvent.onClickSelectDepartamentTo ->
                action = CreateRequestAction.openDepartamentScreenTo(event.listDepartmentHuman)

            is CreateRequestEvent.OnDepartmentFrom -> {
                viewState = viewState.copy(item = viewState.item.copy(departamentFrom = event.newDepartmentHuman))
            }

            is CreateRequestEvent.OnDepartmentTo -> {
                viewState = viewState.copy(item = viewState.item.copy(departamentTo = event.newDepartmentHuman))
            }

            is CreateRequestEvent.onClickSelectUrgently ->
                action = CreateRequestAction.openUrgentlyScreen(event.listUrgencyHuman)

            is CreateRequestEvent.onClickSelectExecutor ->
                action = CreateRequestAction.openExecutorScreen(event.listExecutor)

            is CreateRequestEvent.onChangePatient -> changePatient(event.text)

            is CreateRequestEvent.ChangeFilePickerOption -> changePickFileOption(event.option)
            is CreateRequestEvent.OnFileChosen -> addFiles(event.list)
            is CreateRequestEvent.OnFileDelete -> deleteFiles(event.index)
        }
    }

    init {
        subscribeEvent<EventType.OnStatusRequest> {
            viewState = viewState.copy(item = viewState.item.copy(statusRequest = it.statusRequest))
        }

        subscribeEvent<EventType.OnStatusPatient> {
            viewState = viewState.copy(item = viewState.item.copy(statusPatient = it.statusPatient))
        }

        subscribeEvent<EventType.OnUrgently> {
            viewState = viewState.copy(item = viewState.item.copy(urgency = it.urgently))
        }

        subscribeEvent<EventType.OnExecutor> {
            viewState = viewState.copy(item = viewState.item.copy(executors = it.executor))
        }
        subscribeEvent<EventType.OnDivision> {
            viewState = viewState.copy(
                divisionHuman = it.division,
                item = viewState.item.copy(
                    division = it.division,
                    departamentFrom = viewState.item.departamentFrom.copy(name = ""),
                    departamentTo = viewState.item.departamentTo.copy(name = "")
                )
            )
        }

        viewModelScope.launch {
            subscribeEvent<EventType.OnYearMonthDayRequestPicked> {
                dateCreateRequestCurrent = it.yearMonthDay
                changeCurrentFromYearMonthDayRequest(newYearMonthDay = it.yearMonthDay)
            }
        }
    }

    private fun changeCurrentFromYearMonthDayRequest(
        newYearMonthDay: LocalDate,
    ) {
        viewState =
            viewState.copy(item = viewState.item.copy(date = "${newYearMonthDay}" + "T" + "${timeRequestCurrent}"))
    }

    private fun changePickFileOption(option: PickFileOption) {
        viewModelScope.launch {
            viewState = viewState.copy(
                currentPickFileOption = option
            )
        }
    }

    private fun changePatient(text: String) {
        viewState = if (text.isBlank()) {
            viewState.copy(item = viewState.item.copy(patients = listOf()))
        } else {
            viewState.copy(item = viewState.item.copy(patients = listOf(PatientHuman(name = text))))
        }
        checkEnabledButton()
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

    private fun changeComment(text: String) {
        viewState = if (text.isBlank()) {
            viewState.copy(item = viewState.item.copy(comment = ""))
        } else {
            viewState.copy(item = viewState.item.copy(comment = text))
        }
        checkEnabledButton()
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
                isEnabledButton = viewState.item?.statusRequest != null
                        && viewState.item?.date != null
                        && viewState.dictionary.division != null
                        && viewState.item?.departamentFrom != null
                        && viewState.item?.departamentTo != null
                        && viewState.item?.urgency != null
                        && viewState.item?.patients != null
                        && viewState.item?.statusPatient != null
            )
    }

}
