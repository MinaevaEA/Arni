package com.arni.presentation.ui.screen.pickers.time.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.presentation.ui.components.InfiniteSpinner
import com.arni.presentation.ui.screen.pickers.yearmonth.components.ButtonsBar
import kotlinx.collections.immutable.toPersistentList
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun TimePickerView(
    state: TimePickerState,
    eventConsumer: (TimePickerEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = ArniTheme.colors.neutral_0
            )
            .navigationBarsPadding()
    ) {
        ButtonsBar(
            onCancelBtnClick = {
                eventConsumer(TimePickerEvent.OnBackPressed)
            },
            onReadyBtnClick = {
                eventConsumer(TimePickerEvent.OnConfirm)
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = ArniTheme.colors.black_100,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            // Часы
            InfiniteSpinner(
                modifier = Modifier.width(130.dp),
                list = /*state.hoursList.map { it.toTimeString() }*/(0..23).toPersistentList(),
                firstIndex = 0,
                onSelect = { hour ->
                    eventConsumer(TimePickerEvent.OnHourSelected(hour.toInt()))
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Минуты
            InfiniteSpinner(
                modifier = Modifier.width(50.dp),
                list = /*state.minutesList.map { it.toTimeString() }*/(0..59).toPersistentList(),
                firstIndex = 0,
                onSelect = { minute ->
                    eventConsumer(TimePickerEvent.OnMinuteSelected(minute.toInt()))
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

private fun Int.toTimeString(): String = this.toString().let {
    if (it.length == 1) "0$it" else it
}

@Composable
@Preview
private fun TimePickerViewPreview() {
    ArniTheme {
        ArniTheme {
            TimePickerView(
                state = TimePickerState(
                    hoursList = (0..23).toPersistentList(),
                    minutesList = (0..59).toPersistentList()
                ),
                eventConsumer = {}
            )
        }
    }
}
