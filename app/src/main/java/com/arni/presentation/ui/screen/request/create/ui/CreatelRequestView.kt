package com.arni.presentation.ui.screen.request.create.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.ui.components.PhotoLine
import com.arni.presentation.ui.components.TextFieldInput
import com.arni.presentation.ui.components.TextFieldSelector
import com.arni.presentation.ui.components.TextTitleToolbar
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestEvent
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestState
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestView
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun CreateRequestView(
    state: CreateRequestState, eventConsumer: (CreateRequestEvent) -> Unit
) {
    var showSelectMediaOptionBs = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArniTheme.colors.neutral_0)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        TextTitleToolbar(title = stringResource(R.string.title_add_screen), onBackPressed = {eventConsumer(CreateRequestEvent.onClickBack)})
        LazyColumn() {
            item {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    TextFieldSelector(label = stringResource(id = R.string.status_order), onClick = {})
                    TextFieldSelector(label = stringResource(id = R.string.date_order), onClick = {})
                    TextFieldSelector(label = stringResource(id = R.string.local_order), onClick = {})
                    TextFieldSelector(label = stringResource(id = R.string.from_local), onClick = {})
                    TextFieldSelector(label = stringResource(id = R.string.to_local), onClick = {})
                    TextFieldSelector(label = stringResource(id = R.string.begin_date), onClick = {})
                    TextFieldSelector(label = stringResource(id = R.string.end_date), onClick = {})
                    TextFieldSelector(label = stringResource(id = R.string.draft_order), onClick = {})
                    TextFieldSelector(label = stringResource(id = R.string.checking_order), onClick = {})
                    TextFieldInput(label = stringResource(id = R.string.name_patient_order), enabled = true)
                    TextFieldSelector(label = stringResource(id = R.string.status_patient), onClick = {})
                    TextFieldInput(label = stringResource(id = R.string.comment_order), enabled = true)
                    TextFieldInput(label = stringResource(id = R.string.name_inicial), enabled = true)
                    //todo компонент фото
                    Text(
                        text = stringResource(id = R.string.photo), style = ArniTheme.typography.subhead.regular,
                        color = ArniTheme.colors.neutral_300,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    PhotoLine(
                        addPhotoAction = {
                            showSelectMediaOptionBs.value = true
                        },
                        deletePhotoAction = {},
                        photos = listOf("https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png"),
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun DetailRequestViewPreview() {
    ArniTheme {
        CreateRequestView(state = CreateRequestState(""), eventConsumer = {})
    }
}
