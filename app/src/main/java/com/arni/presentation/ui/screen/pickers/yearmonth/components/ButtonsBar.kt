package com.arni.presentation.ui.screen.pickers.yearmonth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arni.R
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun ButtonsBar(
    onReadyBtnClick: () -> Unit,
    onCancelBtnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = { onCancelBtnClick() },
            contentPadding = PaddingValues(
                all = 0.dp
            ),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Transparent,
                contentColor = ArniTheme.colors.black_100
            )
        ) {
            Text(
                text = stringResource(R.string.cancel),
                style = ArniTheme.typography.title_2.medium
            )
        }

        TextButton(
            onClick = { onReadyBtnClick() },
            contentPadding = PaddingValues(
                all = 0.dp
            ),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Transparent,
                contentColor = ArniTheme.colors.black_100
            )
        ) {
            Text(
                text = stringResource(R.string.ready),
                style = ArniTheme.typography.title_2.medium
            )
        }
    }
}
