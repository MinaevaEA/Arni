package com.arni.presentation.ui.screen.filter.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.ui.components.ButtonFillLarge
import com.arni.presentation.ui.components.TextFieldSelector
import com.arni.presentation.ui.components.TextTitleToolbar
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun FilterView(
    state: FilterState,
    eventConsumer: (FilterEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(ArniTheme.colors.neutral_0)
            .navigationBarsPadding()
    ) {
        TextTitleToolbar(
            title = "Фильтр",
            onBackPressed = {}
        )
        Column(
            modifier = Modifier
                .background(ArniTheme.colors.neutral_0),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldSelector(
                modifier = Modifier.padding(top = 6.dp),
                label = stringResource(R.string.filter_status),
                text = "",
                placeholder = "",
                onClick = { }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                TextFieldSelector(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .weight(1f)
                        .padding(end = 10.dp),
                    label = stringResource(R.string.filter_date),
                    text = "",
                    placeholder = stringResource(id = R.string.undesirable_time_placeholder_start),
                    onClick = { }
                )
                TextFieldSelector(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .weight(1f),
                    label = "",
                    text = "",
                    placeholder = stringResource(id = R.string.undesirable_time_placeholder_end),
                    onClick = { }
                )
            }
            ButtonFillLarge(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                onClick = { },
                text = stringResource(R.string.add_action_filter),
                isEnabled = true
            )
        }
    }
}

@Composable
@Preview
private fun FilterViewPreview() {
    ArniTheme {
        FilterView(FilterState()) {}
    }
}
