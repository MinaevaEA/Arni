package com.arni.presentation.ui.screen.request.general.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.presentation.ui.components.ItemRequest
import com.arni.presentation.ui.components.SelectorToolbarMainScreen
import pro.midev.mec.presentation.ui.style.ArniTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GeneralRequestView(
    state: GeneralRequestState,
    eventConsumer: (GeneralRequestEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArniTheme.colors.neutral_0)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        SelectorToolbarMainScreen(
            "Подразделение",
            onClickFilter = {eventConsumer(GeneralRequestEvent.OnClickFilter)},
            onClickSearch = {},
            onClickAddRequest = { eventConsumer(GeneralRequestEvent.OnClickAddRequest)},
            onNameClick = {})
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ArniTheme.colors.neutral_0)
                .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items = state.tasks) { index, item ->
                ItemRequest(
                    fromDepartment = item.fromDepartament ?:"",
                    toDepartment = item.toDepartament ?:"",
                    nameExecutor = item.nameExecutor?.userName ?:"",
                    isStatus = item.statusRequest?.id ?: 0,
                    Urgency = item.urgently?.title ?:"",
                    onClick = {eventConsumer(GeneralRequestEvent.onClickItem(item, state.human))})
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
private fun GeneralRequestViewPreview() {
    ArniTheme {
        GeneralRequestView(GeneralRequestState()) {}
    }
}
