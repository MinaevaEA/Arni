package com.arni.presentation.ui.screen.select_executor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.model.human.ExecutorHuman
import com.arni.presentation.ui.components.TextTitleToolbar
import com.arni.presentation.ui.screen.pickers.yearmonth.components.ButtonsBar
import com.arni.presentation.ui.screen.pickers.yearmonthday.ui.YearMonthDayPickerEvent
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun SelectExecutorView(
    state: SelectExecutorState,
    eventConsumer: (SelectExecutorEvent) -> Unit
) {

    val checkedState = remember { mutableStateOf(listOf<ExecutorHuman>()) }
    val currentSelected = checkedState.value.toMutableList()
    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .background(color = ArniTheme.colors.neutral_0)
    ) {
/*
        TextTitleToolbar(onBackPressed = {
            eventConsumer.invoke(SelectExecutorEvent.OnBackCLickEvent)
        }, title = stringResource(id = R.string.add_executor))*/
        ButtonsBar(
            onCancelBtnClick = {
                eventConsumer(SelectExecutorEvent.OnBackCLickEvent)
            },
            onReadyBtnClick = {
                eventConsumer(SelectExecutorEvent.SelectExecutor(currentSelected))
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyColumn(modifier = Modifier) {

            itemsIndexed(state.listExecutor) { index, item ->
                Row(modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = checkedState.value.contains(item), onCheckedChange = { selected ->
                        if (selected) {
                            currentSelected.add(item)
                        } else {
                            currentSelected.remove(item)
                        }
                        checkedState.value = currentSelected })
                    Text(
                        modifier = Modifier
                            .weight(1F)
                            .padding(vertical = 12.dp, horizontal = 20.dp),
                        text = item.name,
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
