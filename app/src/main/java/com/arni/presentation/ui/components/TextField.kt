package com.arni.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun TextFieldInput(
    modifier: Modifier = Modifier,
    label: String? = null,
    text: String = "",
    placeholder: String = "",
    @DrawableRes iconStart: Int? = null,
    @DrawableRes iconEnd: Int? = null,
    onEndIconClick: () -> Unit = {},
    enabled: Boolean = true,
    error: String? = null,
    status: Int = 0,
    onValueChange: (value: String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = false
) {

    val hasFocus = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Top, modifier = modifier
    ) {

        label?.let {
            Text(
                text = it,
                style = ArniTheme.typography.subhead.regular,
                color = ArniTheme.colors.neutral_300,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .background(
                    color = if (hasFocus.value)
                        ArniTheme.colors.neutral_100
                    else when (status) {
                        1 -> ArniTheme.colors.neutral_100
                        2 -> ArniTheme.colors.error
                        else -> ArniTheme.colors.neutral_100
                    }
                    ,
                    shape = RoundedCornerShape(16.dp),
                )
                .border(
                    color = if (hasFocus.value)
                        ArniTheme.colors.black_100
                    else when (status) {
                        1 -> ArniTheme.colors.neutral_300
                        2 -> ArniTheme.colors.error
                        else -> ArniTheme.colors.neutral_300
                    },
                    width = 1.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp)
        ) {

            iconStart?.let { res ->
                Icon(
                    painter = painterResource(res), contentDescription = ""
                )
                Spacer(
                    modifier = Modifier.width(4.dp)
                )
            }

            BaseInput(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 13.dp)
                    .onFocusChanged {
                        hasFocus.value = it.hasFocus
                    },
                text = text,
                onValueChange = onValueChange,
                textStyle = ArniTheme.typography.body.regular,
                cursorBrush = SolidColor(ArniTheme.colors.black_100),
                placeholder = placeholder,
                keyboardOptions = keyboardOptions,
                visualTransformation = visualTransformation,
                singleLine = singleLine
            )

            iconEnd?.let { res ->

                Spacer(
                    modifier = Modifier.width(4.dp)
                )

                IconButton(onClick = { onEndIconClick() }) {
                    Icon(
                        painter = painterResource(res),
                        contentDescription = "",
                    )
                }
            }
        }
        AnimatedVisibility(error != null) {
            Text(
                modifier = Modifier.padding(top = 7.dp),
                text = error.orEmpty(),
                style = ArniTheme.typography.footnote.regular,
                color = ArniTheme.colors.error
            )
        }
    }
}

@Composable
fun BaseInput(
    modifier: Modifier = Modifier,
    text: String = "",
    textStyle: TextStyle = ArniTheme.typography.body.regular,
    placeholder: String = "",
    onValueChange: (value: String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    cursorBrush: Brush = SolidColor(ArniTheme.colors.white_100),
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = 10,
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        cursorBrush = cursorBrush,
        decorationBox = { innerTextField ->
            TextFieldDecorationBox(value = text, innerTextField = innerTextField, placeholder = {
                Text(
                    text = placeholder,
                    color = ArniTheme.colors.neutral_300,
                    style = textStyle
                )
            })
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TextFieldDecorationBox(
    value: String,
    innerTextField: @Composable () -> Unit,
    placeholder: @Composable () -> Unit,
) {
    TextFieldDefaults.TextFieldDecorationBox(
        value = value,
        innerTextField = innerTextField,
        enabled = true,
        singleLine = false,
        visualTransformation = VisualTransformation.None,
        placeholder = placeholder,
        interactionSource = remember { MutableInteractionSource() },
        contentPadding = PaddingValues(all = 0.dp)
    )
}


@Composable
@Preview(showBackground = true)
private fun TextInputFieldPreview() {
    ArniTheme {
        Column(
            modifier = Modifier.padding(all = 24.dp), verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TextFieldInput(
                label = "Label",
                placeholder = "Placeholder",
            )
            TextFieldInput(
                label = "Label",
                placeholder = "Placeholder",
                error = "Example Error",
            )
        }
    }
}
