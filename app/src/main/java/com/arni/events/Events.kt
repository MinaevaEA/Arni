package com.arni.events

import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
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
    data class ShowTextToast(val text: String) : EventType
}
