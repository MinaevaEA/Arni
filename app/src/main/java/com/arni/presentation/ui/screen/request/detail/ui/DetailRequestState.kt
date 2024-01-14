package com.arni.presentation.ui.screen.request.detail.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState

@Immutable
data class DetailRequestState(val detail: String): BaseState
sealed interface DetailRequestEvent : BaseEvent {}
