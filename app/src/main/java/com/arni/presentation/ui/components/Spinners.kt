package com.arni.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun <T : Any> InfiniteSpinner(
    modifier: Modifier = Modifier,
    list: List<T>,
    firstIndex: Int = 0,
    onSelect: (T) -> Unit,
    defaultListState: LazyListState? = null
) {
    val listState = defaultListState ?: rememberLazyListState(
        Int.MAX_VALUE / 2 + firstIndex - 1
    )
    val coroutineScope = rememberCoroutineScope()

    val currentIndex = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(listState.isScrollInProgress) {
        coroutineScope.launch {
            listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
        }
        if (!listState.isScrollInProgress) {
            list.getOrNull(currentIndex.value)?.let {
                onSelect(it)
            }
        }
    }

    Box(
        modifier = Modifier
            .height(120.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            if (list.isNotEmpty()) {
                items(
                    count = Int.MAX_VALUE
                ) {
                    val index = Math.floorMod(it - Int.MAX_VALUE / 2, list.size)
                    if (it == listState.firstVisibleItemIndex + 1) {
                        currentIndex.value = index
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        modifier = modifier.alpha(if (it == remember { derivedStateOf { listState.firstVisibleItemIndex } }.value + 1) 1f else 0.3f),
                        text = list[index].toString(),
                        color = ArniTheme.colors.black_100,
                        style = ArniTheme.typography.title_2.regular,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}
