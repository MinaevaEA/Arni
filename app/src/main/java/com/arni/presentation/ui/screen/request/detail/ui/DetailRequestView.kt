package com.arni.presentation.ui.screen.request.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestEvent
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestState
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestView
import kotlinx.collections.immutable.persistentListOf
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun DetailRequestView(
    state: DetailRequestState, eventConsumer: (DetailRequestEvent) -> Unit
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

        TextTitleToolbar(title = stringResource(R.string.app_name), onBackPressed = {})
        LazyColumn() {
            item {
                //todo доделать дизайн
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    TextFieldSelector(label = "Статус заявки", onClick = {})
                    TextFieldSelector(label = "Дата", onClick = {})
                    TextFieldSelector(label = "Подразделение", onClick = {})
                    TextFieldSelector(label = "Откуда", onClick = {})
                    TextFieldSelector(label = "Куда", onClick = {})
                    TextFieldSelector(label = "Начало выполнения", onClick = {})
                    TextFieldSelector(label = "Окончание выполнения", onClick = {})
                    TextFieldSelector(label = "Срочность", onClick = {})
                    TextFieldSelector(label = "Исполнитель(-и)", onClick = {})
                    TextFieldInput(label = "ФИО пациента")
                    TextFieldSelector(label = "Статус пациента", onClick = {})
                    TextFieldInput(label = "Примечание")
                    TextFieldInput(label = "ФИО инициатора")
                    //todo компонент фото
                    Text(
                        text = "Фото", style = ArniTheme.typography.subhead.regular,
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
        DetailRequestView(state = DetailRequestState(""), eventConsumer = {})
    }
}
