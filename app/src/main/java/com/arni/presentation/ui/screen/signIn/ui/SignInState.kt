package com.arni.presentation.ui.screen.signIn.ui

import androidx.compose.runtime.Immutable
import com.arni.presentation.base.BaseAction
import com.arni.presentation.base.BaseEvent
import com.arni.presentation.base.BaseState
import com.arni.presentation.model.human.AuthHuman


@Immutable
data class SignInState(
    val user: AuthHuman = AuthHuman("", ""),
    val isFormValidated: Boolean = false,
    val phoneError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false
) : BaseState

sealed interface SignInEvent : BaseEvent {

    object OnLoginBtnClick : SignInEvent

    data class OnPhoneValueChange(val value: String) : SignInEvent
    data class OnPasswordValueChange(val value: String) : SignInEvent

}

sealed interface SignInAction : BaseAction {

    object OpenNextScreen : SignInAction

    object OpenRestorePasswordScreen : SignInAction

}
