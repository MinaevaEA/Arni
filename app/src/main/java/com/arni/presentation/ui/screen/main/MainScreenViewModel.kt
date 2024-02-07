package com.arni.presentation.ui.screen.main

import com.arni.events.EventType
import com.arni.presentation.base.BaseViewModel
import com.arni.presentation.ext.HideBottomMenuTrigger

class MainScreenViewModel : BaseViewModel<MainScreenState, MainScreenEvent, MainScreenAction>(
    MainScreenState()
) {
    override fun obtainEvent(event: MainScreenEvent) {
        when (event) {
            else -> {}
        }
    }

}
