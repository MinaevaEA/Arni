package com.arni.presentation.ui.screen.filter.ui

import com.arni.presentation.base.BaseViewModel

class FilterViewModel :
    BaseViewModel<FilterState, FilterEvent, FilterAction>(
        FilterState(l = "")
    ) {
    override fun obtainEvent(event: FilterEvent) {
        TODO("Not yet implemented")
    }
}
