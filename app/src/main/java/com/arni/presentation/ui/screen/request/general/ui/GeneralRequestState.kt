package com.arni.presentation.ui.screen.request.general.ui

import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.UserHuman
import com.arni.presentation.ui.screen.pickers.yearmonthday.ui.YearMonthDayPickerState

sealed interface GeneralRequestState : BaseState

object Empty : GeneralRequestState

data class Content(
    val tasks: ListRequestHuman,
    val dictionaryHuman: DictionaryHuman = DictionaryHuman.getDefault(),
    val selectDivision: DivisionHuman = DivisionHuman.getDefault(),
    val human: UserHuman = UserHuman.getDefault(),
    val isCreateRequest: Boolean = false,
    val searchText: String = "",
) : GeneralRequestState {
    open fun clearSearchText(): GeneralRequestState = this

    open fun search(text: String): GeneralRequestState = this
}

sealed interface GeneralRequestEvent : BaseEvent {

    object OnClickAddRequest : GeneralRequestEvent
    object OnClickFilter : GeneralRequestEvent
    class loadNextRequest(val division: DivisionHuman, val listRequestHuman: ListRequestHuman) : GeneralRequestEvent
    class OnSearchEvent(val searchText: String) : GeneralRequestEvent
    object OnClearSearchEvent : GeneralRequestEvent
    object OnBackBtnClick : GeneralRequestEvent
    class onClickDivision(val listDivision: List<DivisionHuman>, val listId: String) : GeneralRequestEvent

    class onClickItem(val listId: String,val item: RequestHuman, val human: UserHuman) : GeneralRequestEvent
}

sealed interface GeneralRequestAction : BaseAction {
    class OpenScreenDetailInfo(
        val listId: String,
        val item: RequestHuman,
        val user: UserHuman,
        val dictionaryHuman: DictionaryHuman,
        val division: DivisionHuman
    ) : GeneralRequestAction

    object OpenScreenAddRequest : GeneralRequestAction
    object OpenScreenFilter : GeneralRequestAction
    object OpenScreenList : GeneralRequestAction
    class OpenListDivision(val listDivision: List<DivisionHuman>, val listID: String) : GeneralRequestAction
    object ExitScreen : GeneralRequestAction
}
