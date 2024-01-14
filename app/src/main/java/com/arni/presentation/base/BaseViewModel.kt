package com.arni.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arni.events.EventType
import com.arni.events.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : BaseState, E : BaseEvent, A : BaseAction>(
    initialState: S
) : ViewModel(), EventHandler<S, E, A> {

    private val _viewState = MutableStateFlow(initialState)
    private val _action = MutableSharedFlow<A>(replay = 0)

    // TODO: Убрать когда в Voyager завезут screen result api
    protected val eventsScope = CoroutineScope(Dispatchers.IO)

    protected var viewState: S
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    protected var action: A? = null
        set(value) {
            viewModelScope.launch {
                if (value != null) {
                    _action.emit(value)
                } else {
                    _action.resetReplayCache()
                }
            }
        }

    override val viewStates: StateFlow<S>
        get() = _viewState.asStateFlow()
    override val viewActions: SharedFlow<A>
        get() = _action.asSharedFlow()

    protected inline fun <reified T : EventType> subscribeEvent(
        crossinline action: (event: T) -> Unit
    ) {
        eventsScope.launch {
            Events.subscribe<T> { event ->
                action(event)
            }
        }
    }

    protected fun publishEvent(type: EventType) {
        eventsScope.launch {
            Events.publish(type)
        }
    }

    protected fun showErrorToast(ex: Exception) {
        publishEvent(EventType.ShowErrorToast(ex))
    }

    // TODO: Убрать когда в Voyager завезут screen result api
    fun cancelEventsScope() {
        eventsScope.coroutineContext.cancelChildren()
    }

}
