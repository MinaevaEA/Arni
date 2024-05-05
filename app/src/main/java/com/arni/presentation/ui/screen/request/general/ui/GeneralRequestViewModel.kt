package com.arni.presentation.ui.screen.request.general.ui

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.data.base.getOrNull
import com.arni.domain.usecase.request.GetChangeRequestUseCase
import com.arni.domain.usecase.request.GetDictionaryUseCase
import com.arni.domain.usecase.request.GetRequestUseCase
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.presentation.model.human.RequestHuman
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
class GeneralRequestViewModel(
    private val getRequestUseCase: GetRequestUseCase,
    private val getDictionaryUseCase: GetDictionaryUseCase,
    private val getUpListUseCase: GetChangeRequestUseCase,
) : BaseViewModel<GeneralRequestState, GeneralRequestEvent, GeneralRequestAction>(Empty) {

    override fun obtainEvent(event: GeneralRequestEvent) {
        when (event) {
            is GeneralRequestEvent.onClickItem -> action =
                GeneralRequestAction.OpenScreenDetailInfo(
                    event.listId,
                    event.item,
                    event.human,
                    dictionary,
                    currentDivision
                )

            is GeneralRequestEvent.onClickDivision -> action =
                GeneralRequestAction.OpenListDivision(event.listDivision, event.listId)

            is GeneralRequestEvent.OnClickAddRequest -> action = GeneralRequestAction.OpenScreenAddRequest(
                event.listId,
                dictionary, currentDivision
            )

            is GeneralRequestEvent.onClickUpdateList -> loadAllNewRequests(event.division, event.listRequestHuman)
            is GeneralRequestEvent.OnBackBtnClick -> action = GeneralRequestAction.ExitScreen
            is GeneralRequestEvent.OnClickFilter -> action = GeneralRequestAction.OpenScreenFilter
            is GeneralRequestEvent.OnClearSearchEvent -> clearSearchText()
            is GeneralRequestEvent.OnSearchEvent -> search(event.searchText)
            is GeneralRequestEvent.loadNextRequest -> loadNextRequestPagination(event.division, event.listRequestHuman)
            //is GeneralRequestEvent.loadChangeRequest -> loadAllChangeRequest(event.division, event.listRequestHuman)
            is GeneralRequestEvent.OnRefresh -> refresh(event.division, event.listRequestHuman)
        }
    }

    private var listDivision: MutableList<DivisionHuman> = mutableListOf()
    private var listRequest: MutableList<RequestHuman> = mutableListOf()
    private var dictionary: DictionaryHuman = DictionaryHuman.getDefault()
    private var currentDivision: DivisionHuman = DivisionHuman.getDefault()

    init {
        viewModelScope.launch {
            val getAllDictionary = viewModelScope.async {
                getDictionaryUseCase().getOrNull()
            }
            val allDictionary = getAllDictionary.await()
            currentDivision = allDictionary?.division?.first() ?: DivisionHuman.getDefault()
            val getRequestsByDictionary = viewModelScope.async {
                currentDivision.guid.let {
                    getRequestUseCase.invoke(
                        limit = 20,
                        divisionGuid = it
                    ).getOrNull()
                }
            }
            val requestsByDictionary = getRequestsByDictionary.await()

            if (allDictionary != null && requestsByDictionary != null) {
                dictionary = allDictionary
                listDivision.addAll(allDictionary.division)
                listRequest.addAll(requestsByDictionary.itemsPage)
                viewState = Content(
                    tasks = requestsByDictionary,
                    dictionaryHuman = allDictionary,
                    selectDivision = listDivision.first()
                )
            }
            launch {
                while (true) {
                    delay(50000)
                    currentDivision.let { division ->
                        requestsByDictionary?.let { dictionary ->
                            checkAllChangeRequests(division, dictionary)
                        }
                    }
                }
            }
        }
        subscribeEvent<EventType.UpdaleList>
        {
            viewModelScope.launch {
                viewState = viewState.changeRefreshingStatus(true)
                val result = getUpListUseCase.invoke(
                    limit = 20,
                    listId = it.listID,
                    divisionGuid = it.divisionHuman.guid,
                    onlycheck = false
                )
                when (result) {
                    is DataStatus.Success -> {
                        viewState = viewState.refreshContentState(listRequest)
                        loadAllRequest(it.listID)
                    }

                    is DataStatus.Loading -> {
                        viewState = viewState.refreshContentState(listRequest)
                    }

                    is DataStatus.Error -> {
                        //println("!!!!!!Error: ${result.ex.message}")
                    }
                }
            }
        }
        //когда меняем подразделение в тулбаре
        subscribeEvent<EventType.OnSelectDivisionGeneral>
        {
            viewModelScope.launch {
                val getRequestOtherDivision = viewModelScope.async {
                    getRequestUseCase.invoke(
                        limit = 20,
                        divisionGuid = it.division.guid,
                        listId = it.listID
                    ).getOrNull()

                }
                val requestsOtherDivision = getRequestOtherDivision.await()
                currentDivision = it.division
                if (requestsOtherDivision != null)
                    viewState = Content(tasks = requestsOtherDivision, selectDivision = it.division)
            }
        }
    }

    private fun refresh(division: DivisionHuman, listRequestHuman: ListRequestHuman) {
        viewModelScope.launch {
            viewState = viewState.changeRefreshingStatus(true)
            val result = getUpListUseCase.invoke(
                limit = 20,
                listId = listRequestHuman.listId,
                divisionGuid = division.guid,
                onlycheck = false
            )
            when (result) {
                is DataStatus.Success -> {
                    viewState = viewState.refreshContentState(result.data.itemsPage)
                    loadAllRequest(listRequestHuman.listId)
                }

                is DataStatus.Loading -> {
                    // viewState = viewState.refreshContentState(listRequest)
                }

                is DataStatus.Error -> {
                    //println("!!!!!!Error: ${result.ex.message}")
                }
            }
        }
    }

    fun loadAllRequest(listId: String) {
        viewModelScope.launch {
            val getAllDictionary = viewModelScope.async {
                getDictionaryUseCase().getOrNull()

            }
            val allDictionaryHuman = getAllDictionary.await()
            val getRequestsByDictionary = viewModelScope.async {
                currentDivision?.guid?.let {
                    getRequestUseCase.invoke(
                        limit = 20,
                        divisionGuid = it,
                        listId = listId
                    ).getOrNull()
                }
            }
            val requestsByDictionary = getRequestsByDictionary.await()

            if (allDictionaryHuman != null && requestsByDictionary != null) {
                dictionary = allDictionaryHuman
                listDivision.addAll(allDictionaryHuman.division)
                listRequest.addAll(requestsByDictionary.itemsPage)
                viewState = Content(
                    tasks = requestsByDictionary,
                    dictionaryHuman = allDictionaryHuman,
                    selectDivision = currentDivision
                )
            }
        }
    }

    private fun loadAllNewRequests(division: DivisionHuman, listRequestHuman: ListRequestHuman) {
        viewModelScope.launch {
            val partOfList = getUpListUseCase.invoke(
                limit = 5,
                listId = listRequestHuman.listId,
                divisionGuid = division.guid,
                pointRef = listRequestHuman.itemsPage.last().guid,
                pointDate = listRequestHuman.itemsPage.last().date,
                onlycheck = false
            ).getOrNull()
            if (partOfList != null)
                viewState = viewState.refreshContentState(partOfList.itemsPage)
            viewState = viewState.changeStatusUpdateList(false)
        }
    }

    private fun checkAllChangeRequests(division: DivisionHuman, listRequestHuman: ListRequestHuman) {
        viewModelScope.launch {
            val partOfList = getUpListUseCase.invoke(
                limit = 5,
                listId = listRequestHuman.listId,
                divisionGuid = division.guid,
                pointRef = listRequestHuman.itemsPage.last().guid,
                pointDate = listRequestHuman.itemsPage.last().date,
                onlycheck = true
            )
            if (partOfList.getOrNull()?.found == true)
                viewState = viewState.changeStatusUpdateList(true)
        }
    }

    private fun loadNextRequestPagination(division: DivisionHuman, listRequestHuman: ListRequestHuman) {
        viewModelScope.launch {
            val partOfList = getRequestUseCase.invoke(
                limit = 5,
                listId = listRequestHuman.listId,
                divisionGuid = division.guid,
                pointRef = listRequestHuman.itemsPage.last().guid,
                pointDate = listRequestHuman.itemsPage.last().date
            ).getOrNull()
            if (partOfList != null)
                viewState = viewState.refreshContentState(partOfList.itemsPage)
        }
    }

    private fun GeneralRequestState.changeRefreshingStatus(isRefreshing: Boolean): GeneralRequestState =
        when (this) {
            is Content -> {
                copy(isRefreshing = isRefreshing)
            }

            Empty -> this
        }

    private fun GeneralRequestState.changeStatusUpdateList(isUpdateList: Boolean): GeneralRequestState =
        when (this) {
            is Content -> {
                copy(isUpdateList = isUpdateList)
            }

            Empty -> this
        }

    private fun GeneralRequestState.refreshContentState(
        addableList: List<RequestHuman>,
        isRefreshing: Boolean = false,
    ): GeneralRequestState =
        when (this) {
            is Content -> {
                copy(
                    tasks = tasks.copy(itemsPage = tasks.itemsPage + addableList),
                    isRefreshing = isRefreshing,
                )
            }

            Empty -> this
        }


    private fun clearSearchText() {
        //viewState = viewState.clearSearchText()
    }

    private fun search(searchText: String) {
        //viewState = viewState.search(searchText)
    }
}
