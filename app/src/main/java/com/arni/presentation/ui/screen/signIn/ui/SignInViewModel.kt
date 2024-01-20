package com.arni.presentation.ui.screen.signIn.ui

import com.arni.domain.usecase.auth.AuthUseCase
import com.arni.presentation.base.BaseViewModel



class SignInViewModel(val authUseCase: AuthUseCase) :
    BaseViewModel<SignInState, SignInEvent, SignInAction>(
        SignInState()
    ) {

    override fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnPhoneValueChange -> changePhone(event.value)
            is SignInEvent.OnPasswordValueChange -> changePassword(event.value)
            //TODO времянка навигации
            is SignInEvent.OnLoginBtnClick -> action = SignInAction.OpenNextScreen
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

