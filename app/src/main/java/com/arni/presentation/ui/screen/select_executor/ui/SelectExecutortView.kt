package com.arni.presentation.ui.screen.select_executor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.ui.components.TextTitleToolbar
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun SelectExecutorView(
    state: SelectExecutorState,
    eventConsumer: (SelectExecutorEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .background(color = ArniTheme.colors.neutral_0)
    ) {

        TextTitleToolbar(onBackPressed = {
            eventConsumer.invoke(SelectExecutorEvent.OnBackCLickEvent)
        }, title = stringResource(id = R.string.add_executor))

        LazyColumn(modifier = Modifier) {

            itemsIndexed(state.listExecutor) { index, item ->
                Row(
                    modifier = Modifier
                        .clickable { eventConsumer(SelectExecutorEvent.SelectExecutor(item)) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1F)
                            .padding(vertical = 12.dp, horizontal = 20.dp),
                        text = item.userName,
                        color = ArniTheme.colors.black_100,
                        style = ArniTheme.typography.body.regular
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun SelectExecutorViewPreview() {
    ArniTheme {
        SelectExecutorView(state = SelectExecutorState(), eventConsumer = {})
    }
}
