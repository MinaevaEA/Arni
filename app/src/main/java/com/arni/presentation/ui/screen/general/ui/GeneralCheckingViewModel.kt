package com.arni.presentation.ui.screen.general.ui

import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.data.base.awaitResult
import com.arni.ext.launchIO
import com.arni.presentation.base.BaseViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class GeneralCheckingViewModel(
) : BaseViewModel<GeneralCheckingState, GeneralCheckingEvent, GeneralCheckingAction>(
    GeneralCheckingState(tasks = persistentListOf())
) {

    private var job: Job? = null

    override fun obtainEvent(event: GeneralCheckingEvent) {
        when (event) {
            is GeneralCheckingEvent.OnCreate -> startTasksLoading()
            is GeneralCheckingEvent.OnClickItem -> {}
            is GeneralCheckingEvent.OnBackBtnClick -> action = GeneralCheckingAction.ExitScreen
        }
    }

    private fun startTasksLoading() {
        job = viewModelScope.launchIO {
            loadTasks()
        }
    }

    private suspend fun loadTasks() {
    }

}
