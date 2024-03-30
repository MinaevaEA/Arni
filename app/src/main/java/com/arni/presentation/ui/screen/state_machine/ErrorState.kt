/*
package com.sport.vibe.presentation.ui.screens.state_machine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sport.vibe.presentation.ui.components.ButtonNoBgMedium
import com.sport.vibe.presentation.ui.style.TemplateTheme
import com.sport.vibe.R

@Composable
fun ErrorStateView(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .background(color = TemplateTheme.colors.white_100_percent)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = 66.dp, end = 66.dp, bottom = 16.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.error_load_data),
                color = TemplateTheme.colors.black_100_percent,
                style = TemplateTheme.typography.titleM.medium
            )

            ButtonNoBgMedium(
                modifier = Modifier
                    .width(258.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { onClick.invoke() },
                text = stringResource(id = R.string.error_repeat_request)
            )
        }
    }
}

@Composable
@Preview
private fun ErrorStateViewPreview() {
    TemplateTheme {
        ErrorStateView {}
    }
}
*/
