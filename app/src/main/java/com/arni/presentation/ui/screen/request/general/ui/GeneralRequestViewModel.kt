package com.arni.presentation.ui.screen.request.general.ui

import androidx.lifecycle.viewModelScope
import com.arni.ext.launchIO
import com.arni.presentation.base.BaseViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.Job

class GeneralRequestViewModel(
) : BaseViewModel<GeneralRequestState, GeneralRequestEvent, GeneralRequestAction>(
    GeneralRequestState(tasks = persistentListOf())
) {

    private var job: Job? = null

    override fun obtainEvent(event: GeneralRequestEvent) {
        when (event) {
            is GeneralRequestEvent.OnCreate -> startTasksLoading()
            is GeneralRequestEvent.OnClickItem -> {}
            is GeneralRequestEvent.OnBackBtnClick -> action = GeneralRequestAction.ExitScreen
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
