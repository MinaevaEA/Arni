package com.arni.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun PhotoLine(
    addPhotoAction: () -> Unit,
    deletePhotoAction: (Int) -> Unit,
    photos: List<String>,
) {

    val rows = photos.chunked(3)

    Column(
        modifier = Modifier.padding(top = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {


        if (photos.isEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .clickable {
                            addPhotoAction()
                        }
                        .weight(1f)
                        .background(shape = RoundedCornerShape(24.dp), color = ArniTheme.colors.neutral_300)
                        .height(164.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(32.dp),
                        painter = painterResource(R.drawable.ic_add_rounded),
                        tint = ArniTheme.colors.black_100,
                        contentDescription = "add"
                    )
                }

                Box(
                    modifier = Modifier
                        .height(164.dp)
                        .weight(1F)
                )

                Box(
                    modifier = Modifier
                        .height(164.dp)
                        .weight(1F)
                )
            }
        }


        rows.forEachIndexed { indexRow, photosAfterChunked ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                photosAfterChunked.forEachIndexed { indexPhoto, photo ->
                    PhotoItem(photo, (indexRow) * 3 + indexPhoto, deletePhotoAction)
                    if ((indexRow) * 3 + indexPhoto + 1 == photos.size && photos.size % 3 != 0) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .clickable {
                                    addPhotoAction()
                                }
                                .weight(1f)
                                .background(shape = RoundedCornerShape(24.dp), color = ArniTheme.colors.neutral_300)
                                .height(164.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(32.dp),
                                painter = painterResource(R.drawable.ic_add_rounded),
                                tint = ArniTheme.colors.black_100,
                                contentDescription = "add"
                            )
                        }

                        if (photosAfterChunked.size == 1) {
                            Box(
                                modifier = Modifier
                                    .height(164.dp)
                                    .weight(1F)
                            )
                        }
                    }
                }

            }

            if (indexRow == rows.size - 1 && photosAfterChunked.size % 3 == 0 && photos.size != 6) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .clickable {
                                addPhotoAction()
                            }
                            .weight(1f)
                            .background(shape = RoundedCornerShape(24.dp), color = ArniTheme.colors.black_100)
                            .height(164.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(32.dp),
                            painter = painterResource(R.drawable.ic_add_rounded),
                            tint = ArniTheme.colors.black_100,
                            contentDescription = "add"
                        )
                    }

                    Box(
                        modifier = Modifier
                            .height(164.dp)
                            .weight(1F)
                    )

                    Box(
                        modifier = Modifier
                            .height(164.dp)
                            .weight(1F)
                    )
                }

            }


        }

    }

}


@Composable
@Preview(backgroundColor = 0xFFFFFFFF)
private fun PhotoItemEmptyPreview() {
    ArniTheme() {
        Column(Modifier.background(color = ArniTheme.colors.neutral_0)) {


            PhotoLine(
                addPhotoAction = {},
                deletePhotoAction = {},
                photos = listOf()
            )
        }
    }
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF)
private fun PhotoItem1Preview() {
    ArniTheme() {
        Column(Modifier.background(color = ArniTheme.colors.neutral_0)) {


            PhotoLine(
                addPhotoAction = {},
                deletePhotoAction = {},
                photos = mutableListOf<String>().apply {
                    repeat(1) {
                        this.add(
                            "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png"
                        )
                    }
                })
        }
    }
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF)
private fun PhotoItem2Preview() {
    ArniTheme() {
        Column(Modifier.background(color = ArniTheme.colors.neutral_0)) {


            PhotoLine(
                addPhotoAction = {},
                deletePhotoAction = {},
                photos = mutableListOf<String>().apply {
                    repeat(2) {
                        this.add(
                            "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png"
                        )
                    }
                })
        }
    }
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF)
private fun PhotoItem3Preview() {
    ArniTheme() {
        Column(Modifier.background(color = ArniTheme.colors.neutral_0)) {


            PhotoLine(
                addPhotoAction = {},
                deletePhotoAction = {},
                photos = mutableListOf<String>().apply {
                    repeat(3) {
                        this.add(
                            "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png"
                        )
                    }
                })
        }
    }
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF)
private fun PhotoItem4Preview() {
    ArniTheme() {
        Column(Modifier.background(color = ArniTheme.colors.neutral_0)) {


            PhotoLine(
                addPhotoAction = {},
                deletePhotoAction = {},
                photos = mutableListOf<String>().apply {
                    repeat(4) {
                        this.add(
                            "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png"
                        )
                    }
                })
        }
    }
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF)
private fun PhotoItem5Preview() {
    ArniTheme() {
        Column(Modifier.background(color = ArniTheme.colors.neutral_0)) {


            PhotoLine(
                addPhotoAction = {},
                deletePhotoAction = {},
                photos = mutableListOf<String>().apply {
                    repeat(5) {
                        this.add(
                            "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png"
                        )
                    }
                })
        }
    }
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF)
private fun PhotoItem6Preview() {
    ArniTheme() {
        Column(Modifier.background(color = ArniTheme.colors.neutral_0)) {


            PhotoLine(
                addPhotoAction = {},
                deletePhotoAction = {},
                photos = mutableListOf<String>().apply {
                    repeat(6) {
                        this.add(
                            "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png"
                        )
                    }
                })
        }
    }
}


