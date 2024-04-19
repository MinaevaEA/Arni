package com.arni.presentation.ui.screen.request.detail.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.data.base.getOrNull
import com.arni.domain.usecase.GetRequestChangeDetailUseCase
import com.arni.domain.usecase.GetRequestEditUseCase
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestViewModel
import com.arni.presentation.ui.screen.request.create.ui.PickFileOption
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DetailRequestViewModel(
    val listId: String,
    val item: RequestHuman,
    dictionary: DictionaryHuman,
    divisionHuman: DivisionHuman,
    val getRequestChangeDetailUseCase: GetRequestChangeDetailUseCase,
    val getRequestEditUseCase: GetRequestEditUseCase
) : BaseViewModel<DetailRequestState, DetailRequestEvent, DetailRequestAction>(
    DetailRequestState(item = item, dictionary = dictionary, divisionHuman = divisionHuman, listId = listId)
) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun obtainEvent(event: DetailRequestEvent) {
        when (val act = event) {
            is DetailRequestEvent.onClickBackList -> action = DetailRequestAction.returnScreenList
            is DetailRequestEvent.onClickToolbarButton -> openEnabledRequest(act.enabled)
            DetailRequestEvent.onClickSelectorTime -> action = DetailRequestAction.OpenTimePicker(
                CreateRequestViewModel.ACT_TIME_ID,
                LocalTime.now(), true
            )

            DetailRequestEvent.onClickSelectorDate -> action =
                DetailRequestAction.OpenYearMonthDayPicker(
                    ARG_START_DATE,
                    LocalDate.parse(viewState.item.startDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    minDate = LocalDate.of(2010, 12, 31),
                    maxDate = LocalDate.of(2080, 12, 31),
                    aaa = "",

                    )

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

            is DetailRequestEvent.OnDepartmentTo -> {
                viewState = viewState.copy(item = viewState.item.copy(departamentTo = act.newDepartmentHuman))
            }
        }
    }

    fun openEnabledRequest(enabled: Boolean) {
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
                //TODO доработать сообщение об ошибке
                else {
                    showErrorToast(Exception())
                    action = DetailRequestAction.returnScreenList
                }
            }
        } else {
            viewModelScope.launch {
                getRequestEditUseCase.invoke(listId, item.guid).collectLatest { result ->
                    when (result) {
                        is DataStatus.Success ->{
                            viewState = viewState.copy(item = result.data.item)
                            action = DetailRequestAction.returnScreenList
                            showErrorToast(Exception("Заявка изменена"))
                        }
                        is DataStatus.Error ->{}
                        is DataStatus.Loading -> {}
                    }
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            viewState = viewState.copy(item = item)
        }

        viewModelScope.launch {
            subscribeEvent<EventType.OnYearMonthDayPicked> {
                if (it.id == ARG_START_DATE) {
                    changeCurrentFromYearMonthDay(it.yearMonthDay)
                } else {
                    changeCurrentToYearMonthDay(it.yearMonthDay)
                }
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

    private fun changeCurrentToYearMonthDay(newYearMonthDay: LocalDate) {
        // viewState = viewState.setEndDate(newYearMonthDay)
    }

    private fun changeCurrentFromYearMonthDay(newYearMonthDay: LocalDate) {
        //viewState = viewState.setStartDate(newYearMonthDay)
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
    }

    companion object {
        const val ARG_START_DATE = 1
        const val ARG_END_DATE = 2
    }
}

