package com.arni.presentation.ui.screen.request.general.ui

import androidx.lifecycle.viewModelScope
import com.arni.data.base.getOrNull
import com.arni.domain.usecase.request.GetDictionaryUseCase
import com.arni.domain.usecase.request.GetRequestUseCase
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.presentation.model.human.RequestHuman
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GeneralRequestViewModel(
    private val getRequestUseCase: GetRequestUseCase,
    private val getDictionaryUseCase: GetDictionaryUseCase
) : BaseViewModel<GeneralRequestState, GeneralRequestEvent, GeneralRequestAction>(Empty) {

    override fun obtainEvent(event: GeneralRequestEvent) {
        when (event) {
            is GeneralRequestEvent.onClickItem -> action =
                GeneralRequestAction.OpenScreenDetailInfo(
                    event.listId,
                    event.item,
                    event.human,
                    dictionary,
                    divisionHuman
                )

            is GeneralRequestEvent.onClickDivision -> action =
                GeneralRequestAction.OpenListDivision(event.listDivision, event.listId)

            is GeneralRequestEvent.OnClickAddRequest -> action = GeneralRequestAction.OpenScreenAddRequest
            is GeneralRequestEvent.OnBackBtnClick -> action = GeneralRequestAction.ExitScreen
            is GeneralRequestEvent.OnClickFilter -> action = GeneralRequestAction.OpenScreenFilter
            is GeneralRequestEvent.OnClearSearchEvent -> clearSearchText()
            is GeneralRequestEvent.OnSearchEvent -> search(event.searchText)
            is GeneralRequestEvent.loadNextRequest -> loadNextRequest(event.division, event.listRequestHuman)
        }
    }

    private var listDivision: MutableList<DivisionHuman> = mutableListOf()
    private var listRequest: MutableList<RequestHuman> = mutableListOf()
    private var dictionary: DictionaryHuman = DictionaryHuman.getDefault()
    private var divisionHuman: DivisionHuman = DivisionHuman.getDefault()

    init {
        viewModelScope.launch {
            val getAllDictionary = viewModelScope.async {
                getDictionaryUseCase().getOrNull()

            }
            val allDictionaryHuman = getAllDictionary.await()
            val getRequestsByDictionary = viewModelScope.async {
                allDictionaryHuman?.division?.first()?.guid?.let {
                    getRequestUseCase.invoke(
                        limit = 7,
                        divisionGuid = it
                    ).getOrNull()
                }
            }
            divisionHuman = allDictionaryHuman?.division?.first() ?: DivisionHuman.getDefault()
            val requestsByDictionary = getRequestsByDictionary.await()

            if (allDictionaryHuman != null && requestsByDictionary != null) {
                dictionary = allDictionaryHuman
                listDivision.addAll(allDictionaryHuman.division)
                listRequest.addAll(requestsByDictionary.itemsPage)
                viewState = Content(
                    tasks = requestsByDictionary,
                    dictionaryHuman = allDictionaryHuman,
                    selectDivision = listDivision.first()

                )
            }
        }
        subscribeEvent<EventType.OnDivisionGeneral> {
            viewModelScope.launch {
                val getRequestOtherDivision = viewModelScope.async {
                    getRequestUseCase.invoke(
                        limit = 7,
                        listId = it.listID, divisionGuid = it.division.guid
                    ).getOrNull()

                }
                val requestsOtherDivision = getRequestOtherDivision.await()
                divisionHuman = it.division
                if (requestsOtherDivision != null)
                    viewState = Content(tasks = requestsOtherDivision, selectDivision = it.division)
            }
        }
    }

    private fun loadNextRequest(division: DivisionHuman, listRequestHuman: ListRequestHuman) {
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

    private fun GeneralRequestState.refreshContentState(addableList: List<RequestHuman>): GeneralRequestState =
        when (this) {
            is Content -> {
                copy(tasks = tasks.copy(itemsPage = tasks.itemsPage + addableList))
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
