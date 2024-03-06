package com.arni.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun AddFilesBS(
    onMakePhotoAction: () -> Unit,
    onOpenGalleryAction: () -> Unit,
    onOpenFileAction: () -> Unit,
    isNeedFileAction: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ArniTheme.colors.white_100)
            .navigationBarsPadding()
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.select_action),
            modifier = Modifier.padding(20.dp),
            color = ArniTheme.colors.black_100,
            style = ArniTheme.typography.title_3.medium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onMakePhotoAction.invoke()
                }
                .padding(20.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_camera_outline),
                contentDescription = "",
                tint = Color.Unspecified
            )
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.make_photo),
                modifier = Modifier.padding(start = 12.dp),
                color = ArniTheme.colors.black_100,
                style = ArniTheme.typography.title_3.medium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onOpenGalleryAction.invoke()
                }
                .padding(20.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_galery_outline),
                contentDescription = "",
                tint = Color.Unspecified
            )
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.open_gallery),
                modifier = Modifier.padding(start = 12.dp),
                color = ArniTheme.colors.black_100,
                style = ArniTheme.typography.title_3.medium
            )
        }
    }
}

@Composable
@Preview
private fun EventBSPreview() {
    ArniTheme {
        AddFilesBS(onMakePhotoAction = {}, onOpenFileAction = {}, onOpenGalleryAction = {})
    }
}
