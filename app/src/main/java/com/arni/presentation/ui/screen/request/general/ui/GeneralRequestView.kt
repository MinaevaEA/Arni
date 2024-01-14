package com.arni.presentation.ui.screen.request.general.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.ui.components.TextTitleToolbar
import kotlinx.collections.immutable.persistentListOf
import pro.midev.mec.presentation.ui.style.ArniTheme

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
        TextTitleToolbar(title = stringResource(R.string.app_name), onBackPressed = {eventConsumer(
            GeneralRequestEvent.OnBackBtnClick
        )})
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ArniTheme.colors.neutral_0)
                .padding(16.dp)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items = state.tasks) { index, item ->
                 }
        }
    }
}

@Composable
@Preview
private fun GeneralRequestViewPreview() {
    ArniTheme {
        GeneralRequestView(GeneralRequestState(persistentListOf(""))){}
    }
}
