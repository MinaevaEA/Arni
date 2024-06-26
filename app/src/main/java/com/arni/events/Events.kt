package com.arni.events

import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.model.human.DispatcherHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.ExecutorHuman
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.model.human.UrgencyHuman
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
    data class UpdaleList(val divisionHuman: DivisionHuman, val listID: String) : EventType
    data class ChangeVisibilityBottomMenu(val isVisible: Boolean) : EventType
    data class ShowErrorToast(val ex: Exception) : EventType
    data class OnTimeRequestPicked(val time: LocalTime) : EventType
    data class OnTimeBeginPicked(val time: LocalTime) : EventType
    data class OnTimeEndPicked(val time: LocalTime) : EventType
    data class OnYearMonthPicked(val yearMonth: YearMonth) : EventType
    data class OnYearMonthDayRequestPicked(val yearMonthDay: LocalDate) : EventType
    data class OnYearMonthDayBeginPicked(val yearMonthDay: LocalDate) : EventType
    data class OnYearMonthDayEndPicked(val yearMonthDay: LocalDate) : EventType
    data class ShowTextToast(val text: String) : EventType

    data class OnStatusRequest(val statusRequest: RequestStatusHuman) : EventType
    data class OnExecutor(val executor: List<ExecutorHuman>) : EventType
    data class OnStatusPatient(val statusPatient: StatusPatientHuman) : EventType
    data class OnUrgently(val urgently: UrgencyHuman) : EventType
    data class OnDispatcher(val dispatcher: DispatcherHuman) : EventType
    class OnSelectDivisionGeneral(val division: DivisionHuman, val listID: String) : EventType
    class OnListID(val listID: String) : EventType
    class OnDivision(val division: DivisionHuman) : EventType
    class OnDepatmentFrom(val depatmentFrom: DepartmentHuman) : EventType
    class OnDepatmentTo(val depatmentTo: DepartmentHuman) : EventType
    class OnChangeAllDepartment(val depatmentTo: DepartmentHuman) : EventType
    class OnUpdateDetailDepartment(val depatments: List<DepartmentHuman>) : EventType
}
