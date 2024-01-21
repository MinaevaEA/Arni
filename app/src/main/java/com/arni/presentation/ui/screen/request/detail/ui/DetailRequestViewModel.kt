package com.arni.presentation.ui.screen.request.detail.ui

import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.model.human.RequestHuman
import kotlinx.coroutines.launch

class DetailRequestViewModel(
    item: RequestHuman
) : BaseViewModel<DetailRequestState, DetailRequestEvent, DetailRequestAction>(
    DetailRequestState(item = item)
) {
    override fun obtainEvent(event: DetailRequestEvent) {
        when (event) {
            is DetailRequestEvent.onClickBackList -> action = DetailRequestAction.returnScreenList
        }
    }

    init {
        viewModelScope.launch {
            //todo разобраться с меню
           // Events.publish(EventType.ChangeVisibilityBottomMenu(true))
        }
    }
}
