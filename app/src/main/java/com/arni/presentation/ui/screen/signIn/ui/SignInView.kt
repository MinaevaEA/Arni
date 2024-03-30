package com.arni.presentation.ui.screen.signIn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.ui.components.ButtonFillLarge
import com.arni.presentation.ui.components.TextFieldInput
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun SignInView(
    state: SignInState,
    eventConsumer: (SignInEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArniTheme.colors.neutral_0)
            .padding(16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.signIn_title),
            style = ArniTheme.typography.title_1.bold,
            color = ArniTheme.colors.black_100,
        )

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.signIn_instruction),
            style = ArniTheme.typography.body.regular
        )

        TextFieldInput(
            modifier = Modifier.padding(top = 40.dp),
            label = stringResource(R.string.signIn_login_label),
            text = state.user.login,
            placeholder = stringResource(R.string.signIn_login_placeholder),
            onValueChange = { eventConsumer(SignInEvent.OnPhoneValueChange(it)) },
            error = "",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            onEndIconClick = { },
            enabled = true
        )

        TextFieldInput(
            modifier = Modifier.padding(top = 16.dp),
            label = stringResource(R.string.signIn_password_label),
            text = state.user.password,
            placeholder = stringResource(id = R.string.signIn_password_placeholder),
            onValueChange = { eventConsumer(SignInEvent.OnPasswordValueChange(it)) },
            error = "",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            onEndIconClick = { },
            enabled = true
        )

        ButtonFillLarge(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            onClick = { eventConsumer(SignInEvent.OnLoginBtnClick) },
            text = stringResource(R.string.signIn_action_1),
            isEnabled = true
        )
    }
}

@Composable
@Preview
private fun SignInViewPreview() {
    ArniTheme {
        SignInView(SignInState()) {}
    }
}
