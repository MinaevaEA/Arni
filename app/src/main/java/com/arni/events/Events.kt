package com.arni.events

import com.arni.presentation.model.human.PatientStatusHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.UrgentlyHuman
import com.arni.presentation.model.human.UserHuman
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import kotlin.coroutines.coroutineContext

object Events {
    private val _events = MutableSharedFlow<BaseEventType>()
    val events = _events.asSharedFlow()

    suspend fun publish(event: BaseEventType) {
        _events.emit(event)
    }

    suspend inline fun <reified T : BaseEventType> subscribe(crossinline onEvent: suspend (T) -> Unit) {
        events.filterIsInstance<T>()
            .collectLatest { event ->
                coroutineContext.ensureActive()
                onEvent(event)
            }
    }
}

interface BaseEventType {

}

sealed interface EventType : BaseEventType {

    data class ChangeVisibilityBottomMenu(val isVisible: Boolean) : EventType
    data class ShowErrorToast(val ex: Exception) : EventType
    data class OnTimePicked(val time: LocalTime, val id: Int) : EventType
    data class OnYearMonthPicked(val yearMonth: YearMonth) : EventType
    data class OnYearMonthDayPicked(val yearMonthDay: LocalDate, val id: Int) : EventType
    data class ShowTextToast(val text: String) : EventType

    data class OnStatusRequest(val statusRequest: RequestStatusHuman): EventType
    data class OnExecutor(val executor: UserHuman): EventType
    data class OnStatusPatient(val statusPatient: PatientStatusHuman): EventType
    data class OnUrgently(val urgently: UrgentlyHuman): EventType
}
