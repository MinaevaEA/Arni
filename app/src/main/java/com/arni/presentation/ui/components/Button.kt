package com.arni.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun ButtonFillLarge(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    backgroundColor: Color = ArniTheme.colors.black_100,
    iconId: Int? = null,
    textStyle: TextStyle = ArniTheme.typography.subhead.medium
) {
    ButtonFillBase(
        modifier = modifier,
        onClick = onClick,
        text = text,
        contentPadding = PaddingValues(all = 16.dp),
        isEnabled = isEnabled,
        isLoading = isLoading,
        backgroundColor = backgroundColor,
        iconId = iconId,
        textStyle = textStyle
    )
}

@Composable
private fun ButtonFillBase(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    isEnabled: Boolean,
    isLoading: Boolean,
    backgroundColor: Color,
    iconId: Int? = null,
    textStyle: TextStyle
) {
    ButtonBase(
        modifier = modifier,
        onClick = onClick,
        text = text,
        contentPadding = contentPadding,
        border = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = ArniTheme.colors.neutral_0,
            disabledBackgroundColor = if (isLoading)
                backgroundColor
            else
                ArniTheme.colors.neutral_200,
            disabledContentColor = if (isLoading)
                ArniTheme.colors.neutral_0
            else
                ArniTheme.colors.neutral_400
        ),
        isEnabled = isEnabled,
        isLoading = isLoading,
        iconId = iconId,
        textStyle = textStyle
    )
}

@Composable
fun ButtonBase(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    border: BorderStroke?,
    colors: ButtonColors,
    isEnabled: Boolean,
    isLoading: Boolean,
    iconId: Int?,
    textStyle: TextStyle
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled && !isLoading,
        shape = RoundedCornerShape(12.dp),
        border = border,
        contentPadding = contentPadding,
        colors = colors,
        elevation = null
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Composable
@Preview(showBackground = true, widthDp = 800)
private fun PreviewButtons() {
    val modifier = Modifier.fillMaxWidth()
    ArniTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ButtonFillLarge(modifier = modifier, onClick = { }, text = "Preview")
                ButtonFillLarge(modifier = modifier, onClick = { }, text = "Preview", isEnabled = false)
            }
        }
    }
}
