package com.arni.presentation.ui.screen.signIn.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState


@Immutable
data class SignInState(
    val username: String = "",
    val password: String = "",
) : BaseState

sealed interface SignInEvent : BaseEvent {

    object OnLoginBtnClick : SignInEvent

    data class OnEmailValueChange(val value: String) : SignInEvent
    data class OnPasswordValueChange(val value: String) : SignInEvent

}

sealed interface SignInAction : BaseAction {

    object OpenNextScreen : SignInAction

    object OpenRestorePasswordScreen : SignInAction

}
