package com.arni.presentation.ui.screen.request.general.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.arni.presentation.ui.components.SearchInput
import com.arni.presentation.ui.components.SelectorToolbarMainScreen
import pro.midev.mec.presentation.ui.style.ArniTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GeneralRequestView(
    state: GeneralRequestState,
    eventConsumer: (GeneralRequestEvent) -> Unit
) {
    when (state) {
        is Content -> DrawContent(state = state, eventConsumer = eventConsumer)
        Empty -> {}
    }
}

@Composable
private fun DrawContent(
    state: Content,
    eventConsumer: (GeneralRequestEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArniTheme.colors.neutral_0)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        SelectorToolbarMainScreen(
            title = state.selectDivision.name,
            onClickFilter = { eventConsumer(GeneralRequestEvent.OnClickFilter) },
            onClickSearch = {},
            onClickAddRequest = { eventConsumer(GeneralRequestEvent.OnClickAddRequest) },
            onNameClick = { eventConsumer(GeneralRequestEvent.onClickDivision(state.dictionaryHuman.division)) })
        SearchInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            text = state.searchText,
            onClearClick = { eventConsumer(GeneralRequestEvent.OnClearSearchEvent) },
            onValueChange = { eventConsumer.invoke(GeneralRequestEvent.OnSearchEvent(it)) }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ArniTheme.colors.neutral_0)
                .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items = state.tasks.itemsPage) { index, item ->
                ItemRequest(
                    fromDepartment = item.departamentFrom.name ?: "",
                    toDepartment = item.departamentTo.name ?: "",
                    nameExecutor = "",
                    isStatus = item.statusRequest.name ?: "",
                    Urgency = item.urgency?.name ?: "",
                    onClick = { eventConsumer(GeneralRequestEvent.onClickItem(item, state.human)) })
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
private fun GeneralRequestViewPreview() {
    ArniTheme {
        GeneralRequestView(Content()) {}
    }
}
