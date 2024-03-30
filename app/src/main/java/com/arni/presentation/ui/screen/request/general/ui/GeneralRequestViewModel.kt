package com.arni.presentation.ui.screen.request.general.ui

import androidx.lifecycle.viewModelScope
import com.arni.data.base.getOrNull
import com.arni.domain.usecase.request.DictionaryUseCase
import com.arni.domain.usecase.request.GetRequestUseCase
import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.RequestHuman
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GeneralRequestViewModel(
    private val getRequestUseCase: GetRequestUseCase,
    private val getDictionaryUseCase: DictionaryUseCase
) : BaseViewModel<GeneralRequestState, GeneralRequestEvent, GeneralRequestAction>(Empty) {
    val allRequest: MutableList<RequestHuman> = mutableListOf()

    override fun obtainEvent(event: GeneralRequestEvent) {
        when (event) {
            is GeneralRequestEvent.onClickItem -> action =
                GeneralRequestAction.OpenScreenDetailInfo(event.item, event.human)

            is GeneralRequestEvent.onClickDivision -> action = GeneralRequestAction.OpenListDivision(event.listDivision)
            is GeneralRequestEvent.OnClickAddRequest -> action = GeneralRequestAction.OpenScreenAddRequest
            is GeneralRequestEvent.OnBackBtnClick -> action = GeneralRequestAction.ExitScreen
            is GeneralRequestEvent.OnClickFilter -> action = GeneralRequestAction.OpenScreenFilter
            GeneralRequestEvent.OnClearSearchEvent -> clearSearchText()
            is GeneralRequestEvent.OnSearchEvent -> search(event.searchText)
            else -> {}
        }
    }

    private var listDivision: MutableList<DivisionHuman> = mutableListOf()

    init {
        viewModelScope.launch {
            val async1 = viewModelScope.async {
                getDictionaryUseCase().getOrNull()
            }

            val async2 = viewModelScope.async {
                getRequestUseCase.invoke(
                    limit = 3,
                    listId = "bde674c2-fb09-4574-882b-9a243ebad37e",
                    divisionGuid = "2c174b8e-5d2f-11ea-80f0-0050569bd24c"
                ).getOrNull()
            }

            val r1 = async1.await()
            val r2 = async2.await()

            if (r1 != null && r2 != null) {
                listDivision.addAll(r1.division)
                viewState = Content(
                    tasks = r2,
                    dictionaryHuman = r1,
                    selectDivision = listDivision.first()

                )
            }
        }
        subscribeEvent<EventType.OnDivision> {
            viewModelScope.launch {
                val r = viewModelScope.async {
                    getRequestUseCase.invoke(
                        limit = 3,
                        listId = "67b38ac3-78d2-4642-8716-23e38ffe48af", divisionGuid = it.division.guid
                    ).getOrNull()

                }
                val r3 = r.await()
                if (r3 != null)
                    viewState = Content(tasks = r3, selectDivision = it.division)
            }
        }
    }


    private fun clearSearchText() {
        //viewState = viewState.clearSearchText()
    }

    private fun search(searchText: String) {
        //viewState = viewState.search(searchText)
    }
}
