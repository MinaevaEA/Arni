package com.arni.presentation.ui.screen.request.detail.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.arni.data.base.getOrNull
import com.arni.domain.usecase.GetRequestChangeDetailUseCase
import com.arni.domain.usecase.GetRequestEditUseCase
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.presentation.model.human.PatientHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.ui.screen.request.create.ui.PickFileOption
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DetailRequestViewModel(
    val listId: String,
    val item: RequestHuman,
    dictionary: DictionaryHuman,
    val divisionHuman: DivisionHuman,
    val getRequestChangeDetailUseCase: GetRequestChangeDetailUseCase,
    val getRequestEditUseCase: GetRequestEditUseCase
) : BaseViewModel<DetailRequestState, DetailRequestEvent, DetailRequestAction>(
    DetailRequestState(item = item, dictionary = dictionary, divisionHuman = divisionHuman, listId = listId)
) {
    var currentDivision: DivisionHuman = item.division
    var dateRequestCurrent = LocalDate.now()
    var dateBeginCurrent = LocalDate.now()
    var dateEndCurrent = LocalDate.now()
    var timeRequestCurrent = LocalTime.of(0, 0, 1)
    var timeBeginCurrent = LocalTime.of(0, 0, 1)
    var timeEndCurrent = LocalTime.of(0, 0, 1)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun obtainEvent(event: DetailRequestEvent) {
        when (val act = event) {
            is DetailRequestEvent.onClickBackList -> action = DetailRequestAction.returnScreenList
            is DetailRequestEvent.onClickToolbarButton -> openEnabledRequest(act.item, act.enabled)
            DetailRequestEvent.onClickSelectorTimeRequest -> action = DetailRequestAction.OpenTimePickerRequest(
                LocalTime.parse(viewState.item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                minDate = LocalTime.of(0, 0, 0),
                maxDate = LocalTime.of(23, 59, 59),
                1
            )

            DetailRequestEvent.onClickSelectorTimeBegin -> action = DetailRequestAction.OpenTimePickerStart(
                LocalTime.parse(viewState.item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                minDate = LocalTime.of(0, 0, 0),
                maxDate = LocalTime.of(23, 59, 59),
                2
            )

            DetailRequestEvent.onClickSelectorTimeEnd -> action = DetailRequestAction.OpenTimePickerEnd(
                LocalTime.parse(viewState.item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                minDate = LocalTime.of(0, 0, 0),
                maxDate = LocalTime.of(23, 59, 59),
                3
            )

            is DetailRequestEvent.onChangePatient -> changePatient(act.text)
            DetailRequestEvent.onClickSelectorDateRequest -> {
                action = DetailRequestAction.OpenYearMonthDayPickerRequest(
                    LocalDate.parse(viewState.item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    minDate = LocalDate.of(2010, 12, 31),
                    maxDate = LocalDate.of(2080, 12, 31),
                    1
                )
            }

            DetailRequestEvent.onClickSelectorDateBegin -> {
                action = DetailRequestAction.OpenYearMonthDayPickerStart(
                    LocalDate.parse(viewState.item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    minDate = LocalDate.of(2010, 12, 31),
                    maxDate = LocalDate.of(2080, 12, 31),
                    2
                )
            }

            DetailRequestEvent.onClickSelectorDateEnd -> {
                action = DetailRequestAction.OpenYearMonthDayPickerEnd(
                    LocalDate.parse(viewState.item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    minDate = LocalDate.of(2010, 12, 31),
                    maxDate = LocalDate.of(2080, 12, 31),
                    3
                )
            }

            is DetailRequestEvent.onChangeDescription -> changeComment(act.text)

            is DetailRequestEvent.onChangeDataRequest -> changeDataRequest(act.text)

            is DetailRequestEvent.onClickSelectStatus ->
                action = DetailRequestAction.openRequestStatusScreen(act.listStatus)

            is DetailRequestEvent.onClickSelectDivision ->
                action = DetailRequestAction.openDivisionScreen(act.listDivision)

            is DetailRequestEvent.onClickSelectDepartamentFrom ->
                action = DetailRequestAction.openDepartamentScreenFrom(act.listDepartmentHuman)

            is DetailRequestEvent.onClickSelectDepartamentTo ->
                action = DetailRequestAction.openDepartamentScreenTo(act.listDepartmentHuman)

            is DetailRequestEvent.onClickSelectUrgently ->
                action = DetailRequestAction.openUrgentlyScreen(act.listUrgencyHuman)

            is DetailRequestEvent.onClickSelectDispatchers ->
                action = DetailRequestAction.openDispatcherScreen(act.listDispatcherHuman)

            is DetailRequestEvent.onClickSelectExecutor ->
                action = DetailRequestAction.openExecutorScreen(act.listExecutor)

            is DetailRequestEvent.onClickSelectStatusPatient ->
                action = DetailRequestAction.openStatusPatientScreen(act.listStatusPatient)

            is DetailRequestEvent.ChangeFilePickerOption -> changePickFileOption(act.option)
            is DetailRequestEvent.OnFileChosen -> addFiles(act.list)
            is DetailRequestEvent.OnFileDelete -> deleteFiles(act.index)
            is DetailRequestEvent.OnDepartmentFrom -> {
                viewState = viewState.copy(item = viewState.item.copy(departamentFrom = act.newDepartmentHuman))
            }

            is DetailRequestEvent.isDelete -> viewState =
                viewState.copy(item = viewState.item.copy(markdelete = act.isDelete))

            is DetailRequestEvent.OnDepartmentTo -> {
                viewState = viewState.copy(item = viewState.item.copy(departamentTo = act.newDepartmentHuman))
            }
        }
    }

    fun openEnabledRequest(item: RequestHuman, enabled: Boolean) {
        if (enabled) {
            viewModelScope.launch {
                val isChange = viewModelScope.async {
                    getRequestChangeDetailUseCase.invoke(
                        listId = listId,
                        requestGuid = item.guid
                    ).getOrNull()
                }
                val change = isChange.await()
                if (change?.anotheritemver == false) {
                    viewState = viewState.copy(enabled = enabled)
                    checkEnabledButton()
                }

                else {
                    publishEvent(EventType.UpdaleList(currentDivision, listId))
                    showErrorToast(Exception("Обновите список"))
                    action = DetailRequestAction.returnScreenList
                }
            }
        } else {
            viewModelScope.launch {
                val resultChange = getRequestEditUseCase.invoke(listId = listId, item.guid, item).getOrNull()
                if (resultChange?.anotheritemver == false) {
                    publishEvent(EventType.UpdaleList(currentDivision, listId))
                    viewState = viewState.copy(enabled = enabled)
                    action = DetailRequestAction.returnScreenList
                    showErrorToast(Exception("Заявка изменена"))
                } else {
                    showErrorToast(Exception("Обновите список"))
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            viewState = viewState.copy(item = item)
        }

        viewModelScope.launch {
            subscribeEvent<EventType.OnYearMonthDayRequestPicked> {
                dateRequestCurrent = it.yearMonthDay
                changeCurrentFromYearMonthDayRequest(newYearMonthDay = it.yearMonthDay)
            }

            subscribeEvent<EventType.OnYearMonthDayBeginPicked> {
                dateBeginCurrent = it.yearMonthDay
                changeCurrentFromYearMonthDayBegin(newYearMonthDay = it.yearMonthDay)
            }
            subscribeEvent<EventType.OnYearMonthDayEndPicked> {
                dateEndCurrent = it.yearMonthDay
                changeCurrentFromYearMonthDayEnd(newYearMonthDay = it.yearMonthDay)
            }
            subscribeEvent<EventType.OnTimeRequestPicked> {
                timeRequestCurrent = it.time
                changeCurrentFromTimeRequest(newTime = it.time)
            }
            subscribeEvent<EventType.OnTimeBeginPicked> {
                timeBeginCurrent = it.time
                changeCurrentFromTimeBegin(newTime = it.time)
            }
            subscribeEvent<EventType.OnTimeEndPicked> {
                timeEndCurrent = it.time
                changeCurrentFromTimeEnd(newTime = it.time)
            }
        }
    }

    private fun changePickFileOption(option: PickFileOption) {
        viewModelScope.launch {
            viewState = viewState.copy(
                currentPickFileOption = option
            )
        }
    }

    private fun changeCurrentFromYearMonthDayRequest(
        newYearMonthDay: LocalDate,
    ) {
        viewState =
            viewState.copy(item = viewState.item.copy(date = "${newYearMonthDay}" + "T" + "${timeRequestCurrent}"))
    }

    private fun changeCurrentFromYearMonthDayBegin(
        newYearMonthDay: LocalDate,
    ) {
        viewState =
            viewState.copy(item = viewState.item.copy(startDate = "${newYearMonthDay}" + "T" + "${timeRequestCurrent}"))
    }

    private fun changeCurrentFromYearMonthDayEnd(
        newYearMonthDay: LocalDate,
    ) {
        viewState =
            viewState.copy(item = viewState.item.copy(endDate = "${newYearMonthDay}" + "T" + "${timeRequestCurrent}"))
    }

    private fun changeCurrentFromTimeRequest(
        newTime: LocalTime? = LocalTime.now()
    ) {
        timeRequestCurrent = newTime
        viewState = viewState.copy(item = viewState.item.copy(date = "${dateRequestCurrent}" + "T" + "${newTime}"))
    }

    private fun changeCurrentFromTimeBegin(
        newTime: LocalTime? = LocalTime.now()
    ) {
        timeBeginCurrent = newTime
        viewState = viewState.copy(item = viewState.item.copy(startDate = "${dateBeginCurrent}" + "T" + "${newTime}"))
    }

    private fun changeCurrentFromTimeEnd(
        newTime: LocalTime? = LocalTime.now()
    ) {
        timeEndCurrent = newTime
        viewState = viewState.copy(item = viewState.item.copy(endDate = "${dateEndCurrent}" + "T" + "${newTime}"))
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

    private fun changePatient(text: String) {
        viewState = if (text.isBlank()) {
            viewState.copy(item = viewState.item.copy(patients = listOf()))
        } else {
            viewState.copy(item = viewState.item.copy(patients = listOf(PatientHuman(name = text))))
        }
        checkEnabledButton()
    }

    private fun changeComment(text: String) {
        viewState = if (text.isBlank()) {
            viewState.copy(item = viewState.item.copy(comment = ""))
        } else {
            viewState.copy(item = viewState.item.copy(comment = text))
        }
        checkEnabledButton()
    }


    private fun changeDataRequest(text: String) {
        val format = SimpleDateFormat("")
        val targetFormatDate = SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss")
        val date = targetFormatDate.parse(text)
        val dateRequest = targetFormatDate.parse(date.toString())
        viewState = if (text.isBlank()) {
            viewState.copy(item = viewState.item.copy(date = ""))
        } else {
            viewState.copy(item = viewState.item.copy(date = dateRequest.toString()))
        }
        checkEnabledButton()
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
        subscribeEvent<EventType.OnDispatcher> {
            viewState = viewState.copy(item = viewState.item.copy(dispatcher = it.dispatcher))
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
                    departamentTo = viewState.item.departamentTo.copy(name = ""),
                    dispatcher = viewState.item.dispatcher.copy(name = "")
                )
            )
            currentDivision = it.division
        }
    }
}

