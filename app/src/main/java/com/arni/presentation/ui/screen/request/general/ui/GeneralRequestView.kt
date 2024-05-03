package com.arni.presentation.ui.screen.request.general.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.arni.presentation.ext.isFirstItemVisible
import com.arni.presentation.ext.isLastItemVisible
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.presentation.ui.components.ButtonFillLarge
import com.arni.presentation.ui.components.CustomPullRefreshIndicator
import com.arni.presentation.ui.components.ItemRequest
import com.arni.presentation.ui.components.SearchInput
import com.arni.presentation.ui.components.SelectorToolbarMainScreen
import com.arni.presentation.ui.components.pullRefreshCustom
import com.arni.presentation.ui.components.rememberCustomPullRefreshState
import com.arni.presentation.ui.screen.state_machine.LoadingStateView
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import pro.midev.mec.presentation.ui.style.ArniTheme
import kotlin.system.measureNanoTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GeneralRequestView(
    state: GeneralRequestState,
    eventConsumer: (GeneralRequestEvent) -> Unit
) {

    when (state) {
        is Content -> DrawContent(state = state, eventConsumer = eventConsumer)

        Empty -> {
            LoadingStateView()
        }
    }
}

data class ScrollContext(
    val isTop: Boolean,
    val isBottom: Boolean,
)


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DrawContent(
    state: Content,
    eventConsumer: (GeneralRequestEvent) -> Unit,
) {
    val ptrState = rememberCustomPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = {
            eventConsumer(GeneralRequestEvent.OnRefresh(state.selectDivision, state.tasks))
        })
    Box(
        modifier = Modifier
            .zIndex(-1F)
            .pullRefreshCustom(ptrState)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .zIndex(2F)
                    .align(Alignment.CenterHorizontally)
                    .background(color = Color.Unspecified)
            ) {
                if (state.isUpdateList)
                    ButtonFillLarge(
                        Modifier
                            .align(Alignment.TopCenter),
                        onClick = {
                            eventConsumer(
                                GeneralRequestEvent.onClickUpdateList(
                                    state.selectDivision,
                                    state.tasks
                                )
                            )
                        },
                        text = "Обновить список",
                        isEnabled = true
                    )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ArniTheme.colors.neutral_0)
                .navigationBarsPadding()
        ) {
            val listState = rememberLazyListState()
            val scrollContext = rememberScrollContext(listState)
            SelectorToolbarMainScreen(
                title = state.selectDivision.name,
                onClickFilter = { eventConsumer(GeneralRequestEvent.OnClickFilter) },
                onClickSearch = {},
                onClickAddRequest = {
                    eventConsumer(
                        GeneralRequestEvent.OnClickAddRequest(
                            state.tasks.listId,
                            state.human
                        )
                    )
                },
                onNameClick = {
                    eventConsumer(
                        GeneralRequestEvent.onClickDivision(
                            state.dictionaryHuman.division,
                            state.tasks.listId
                        )
                    )
                })
        /*       SearchInput(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(all = 10.dp),
                   text = state.searchText,
                   onClearClick = { eventConsumer(GeneralRequestEvent.OnClearSearchEvent) },
                   onValueChange = { eventConsumer.invoke(GeneralRequestEvent.OnSearchEvent(it)) }
               )
*/
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ArniTheme.colors.neutral_0)
                    .padding(start = 5.dp, end = 5.dp, top = 1.dp, bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = listState
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .zIndex(1F)
                    ) {
                        if (state.isLoading.not())
                            CustomPullRefreshIndicator(
                                state.isRefreshing,
                                ptrState,
                                Modifier
                                    .align(Alignment.TopCenter)
                            )
                    }
                }
                itemsIndexed(items = state.tasks.itemsPage) { index, item ->
                    ItemRequest(
                        fromDepartment = item.departamentFrom.name ?: "",
                        toDepartment = item.departamentTo.name ?: "",
                        nameExecutor = "${item.executors?.joinToString { it.name }}" /*+ item.executors.size*/,
                        isStatus = item.statusRequest.name ?: "",
                        Urgency = item.urgency?.name ?: "",
                        isStatusDelete = item.markdelete,
                        number = item.number,
                        onClick = {
                            eventConsumer(
                                GeneralRequestEvent.onClickItem(
                                    state.tasks.listId,
                                    item,
                                    state.human
                                )
                            )
                        })
                }
                if (scrollContext.isBottom) {
                    eventConsumer(GeneralRequestEvent.loadNextRequest(state.selectDivision, state.tasks))
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
private fun GeneralRequestViewPreview() {
    ArniTheme {
        GeneralRequestView(Content(ListRequestHuman("", itemsPage = listOf(), found = false))) {}
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
