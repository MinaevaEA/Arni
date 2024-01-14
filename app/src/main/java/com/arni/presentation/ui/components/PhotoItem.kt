package com.arni.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arni.R
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun RowScope.PhotoItem(photo: String, index: Int, deletePhotoAction: (Int) -> Unit) {
    Box(modifier = Modifier
        .height(164.dp)
        .weight(1F)) {
      /*  AsyncImage(
            model = photo,
            placeholder = painterResource(R.drawable.ic_next),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .fillMaxHeight()
        )*/
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 9.67.dp, top = 12.dp)
                .size(26.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = ArniTheme.colors.neutral_300)
                .clickable {

                }) {
            Icon(
                modifier = Modifier
                    .clickable {
                        deletePhotoAction(index)
                    }
                    .size(26.dp)
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.ic_check_indeterminate_small_rounded),
                tint = ArniTheme.colors.white_100,
                contentDescription = "close"
            )
        }
    }
}

@Composable
@Preview()
private fun PhotoItemPreview() {
    ArniTheme() {
        Row(Modifier.background(color = ArniTheme.colors.neutral_0)) {
            PhotoItem(
                photo = "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png",
                index = 1,
                deletePhotoAction = {})
        }
    }
}
