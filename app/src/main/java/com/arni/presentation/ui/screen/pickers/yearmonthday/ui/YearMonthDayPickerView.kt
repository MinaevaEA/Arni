package com.arni.presentation.ui.screen.pickers.yearmonthday.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.presentation.ui.components.InfiniteSpinner
import com.arni.presentation.ui.screen.pickers.yearmonth.components.ButtonsBar
import com.arni.presentation.util.CalendarUtils
import kotlinx.collections.immutable.toPersistentList
import pro.midev.mec.presentation.ui.style.ArniTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearMonthDayPickerView(
    state: YearMonthDayPickerState,
    eventConsumer: (YearMonthDayPickerEvent) -> Unit
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
                eventConsumer(YearMonthDayPickerEvent.OnBackPressed)
            },
            onReadyBtnClick = {
                eventConsumer(YearMonthDayPickerEvent.OnConfirm)
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
            InfiniteSpinner(
                modifier = Modifier.width(50.dp),
                list = state.daysList.map { it.toTimeString() },
                firstIndex = 0,
                onSelect = { day ->
                    eventConsumer(YearMonthDayPickerEvent.OnDaySelected(day.toInt()))
                }
            )
            Spacer(modifier = Modifier.width(24.dp))
            InfiniteSpinner(
                modifier = Modifier.width(130.dp),
                list = state.monthList.map { CalendarUtils.getStandaloneMonthName(LocalDate.of(1970, it, 1).month) },
                firstIndex = 0,
                onSelect = { month ->
                    eventConsumer(YearMonthDayPickerEvent.OnMonthSelected(CalendarUtils.getMonthToInt(month)))
                }
            )
            Spacer(modifier = Modifier.width(24.dp))
            InfiniteSpinner(
                modifier = Modifier.wrapContentWidth(),
                list = state.yearList.map { it.toTimeString() },
                firstIndex = 0,
                onSelect = { year ->
                    eventConsumer(YearMonthDayPickerEvent.OnYearSelected(year.toInt()))
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

private fun Int.toTimeString(): String = this.toString().let {
    if (it.length == 1) "0$it" else it
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
private fun YearMonthPickerViewPreview() {
    ArniTheme {
        ArniTheme {
            YearMonthDayPickerView(
                state = YearMonthDayPickerState(
                    daysList = (0..31).toPersistentList(),
                    monthList = (1..12).toPersistentList(),
                    yearList = (1970..2023).toPersistentList()
                ),
                eventConsumer = {}
            )
        }
    }
}
