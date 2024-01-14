package com.arni.presentation.ui.screen.signIn.ui

import com.arni.domain.usecase.auth.AuthUseCase
import com.arni.presentation.base.BaseViewModel



class SignInViewModel(val authUseCase: AuthUseCase) :
    BaseViewModel<SignInState, SignInEvent, SignInAction>(
        SignInState()
    ) {

    override fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnEmailValueChange -> changePhone(event.value)
            is SignInEvent.OnPasswordValueChange -> changePassword(event.value)
            is SignInEvent.OnLoginBtnClick -> signIn()
        }
    }


    private fun changePhone(phone: String) {
        viewState = viewState.copy(
            username = phone
        )
        validate()
    }

    private fun changePassword(password: String) {
        viewState = viewState.copy(
            password = password,
        )
        validate()
    }

    private fun validate() {
    }

    fun signIn() {
    }
}

