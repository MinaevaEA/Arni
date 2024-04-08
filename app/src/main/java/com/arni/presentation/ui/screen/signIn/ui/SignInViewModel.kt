package com.arni.presentation.ui.screen.signIn.ui

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.arni.data.base.DataStatus
import com.arni.domain.usecase.auth.AuthUseCase
import com.arni.presentation.base.BaseViewModel
import com.arni.remote.exceptions.ArniRemoteException
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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
        viewState = viewState.copy(user = viewState.user.copy(phone = phone), phoneError = null)
        validate()
    }

    private fun changePassword(password: String) {
        viewState = viewState.copy(user = viewState.user.copy(password = password), passwordError = null)
        validate()
    }

    private fun validate() {
        val phone = viewState.user.phone
        val password = viewState.user.password

        viewState = viewState.copy(
            isFormValidated = phone.isNotBlank() && password.isNotBlank()
                    && Patterns.PHONE.matcher(phone).matches()
        )
    }

    fun signIn() {
        viewModelScope.launch {
            authUseCase.invoke(viewState.user)
                .collectLatest { result ->
                    when (result) {
                        is DataStatus.Success -> action =
                            SignInAction.OpenNextScreen

                        is DataStatus.Error -> {
                            if(result.ex is ArniRemoteException) {
                                when(result.ex.message) {
                                    //TODO будет полезна, когда будет проверка на бэке
                                    "Неверный пароль" -> viewState = viewState
                                        .copy(passwordError = result.ex.message)
                                    "Неверный телефон" -> viewState = viewState
                                        .copy(phoneError = result.ex.message)
                                    else -> showErrorToast(result.ex)
                                }
                            } else {
                                showErrorToast(result.ex)
                                viewState = viewState.copy(passwordError = "Проверьте телефон")
                                 viewState = viewState.copy(phoneError = "Проверьте пароль")
                            }
                        }

                        is DataStatus.Loading -> {}
                    }
                }
        }
    }
}

