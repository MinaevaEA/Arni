package com.arni.presentation.ui.screen.signIn.ui

import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.auth.AuthUseCase
import com.arni.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.reflect.InvocationTargetException


class SignInViewModel(val authUseCase: AuthUseCase) :
    BaseViewModel<SignInState, SignInEvent, SignInAction>(
        SignInState()
    ) {

    override fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnPhoneValueChange -> changePhone(event.value)
            is SignInEvent.OnPasswordValueChange -> changePassword(event.value)
            is SignInEvent.OnLoginBtnClick -> signIn()
        }
    }


    private fun changePhone(phone: String) {
        viewState = viewState.copy(user = viewState.user.copy(login = phone))
        validate()
    }

    private fun changePassword(password: String) {
        viewState = viewState.copy(user = viewState.user.copy(password = password))
        validate()
    }

    private fun validate() {
    }

    fun signIn() {
        viewModelScope.launch {
            try {

                authUseCase.invoke(viewState.user)
                    .collectLatest { result ->
                        when (result) {
                            is DataStatus.Success -> action =
                                SignInAction.OpenNextScreen

                            is DataStatus.Error -> {
                                when (result.ex.message) {
                                    else -> showErrorToast(result.ex)
                                }
                            }

                            is DataStatus.Loading -> viewState = viewState.copy()
                        }
                    }
            } catch (e: InvocationTargetException) {
                Timber.tag("1111111111").d(e.cause)
            }
        }
    }
}

