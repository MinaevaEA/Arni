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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.presentation.ext.isFirstItemVisible
import com.arni.presentation.ext.isLastItemVisible
import com.arni.presentation.model.human.ListRequestHuman
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

data class ScrollContext(
    val isTop: Boolean,
    val isBottom: Boolean,
)


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
        val listState = rememberLazyListState()
        val scrollContext = rememberScrollContext(listState)
        SelectorToolbarMainScreen(
            title = state.selectDivision.name,
            onClickFilter = { eventConsumer(GeneralRequestEvent.OnClickFilter) },
            onClickSearch = {},
            onClickAddRequest = { eventConsumer(GeneralRequestEvent.OnClickAddRequest) },
            onNameClick = {
                eventConsumer(
                    GeneralRequestEvent.onClickDivision(
                        state.dictionaryHuman.division,
                        state.tasks.listId
                    )
                )
            })
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
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {

            itemsIndexed(items = state.tasks.itemsPage) { index, item ->
                ItemRequest(
                    fromDepartment = item.departamentFrom.name ?: "",
                    toDepartment = item.departamentTo.name ?: "",
                    nameExecutor = "${item.executors.joinToString { it.name }}" /*+ item.executors.size*/,
                    isStatus = item.statusRequest.name ?: "",
                    Urgency = item.urgency?.name ?: "",
                    onClick = { eventConsumer(GeneralRequestEvent.onClickItem(item, state.human)) })
            }
            if (scrollContext.isBottom) {
                eventConsumer(GeneralRequestEvent.loadNextRequest(state.selectDivision.guid, state.tasks))
                //state.tasks.itemsPage.size
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
private fun GeneralRequestViewPreview() {
    ArniTheme {
        GeneralRequestView(Content(ListRequestHuman("", listOf()))) {}
    }
}

@Composable
fun rememberScrollContext(listState: LazyListState): ScrollContext {
    val scrollContext by remember {
        derivedStateOf {
            ScrollContext(
                isTop = listState.isFirstItemVisible,
                isBottom = listState.isLastItemVisible
            )
        }
    }
    return scrollContext
}
