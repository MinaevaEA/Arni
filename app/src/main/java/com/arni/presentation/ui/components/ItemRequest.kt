package com.arni.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.enum.StatusRequests
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun ItemRequest(
    fromDepartment: String,
    toDepartment: String,
    nameExecutor: String?,
    isStatus: String,
    isStatusDelete: Boolean,
    Urgency: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = ArniTheme.colors.black_100,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .background(color = ArniTheme.colors.white_100)
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Column(
            modifier = Modifier
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Column(
                        modifier = Modifier
                            .padding(end = 2.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .border(
                                1.dp, color = when (isStatus) {
                                    StatusRequests.parse(StatusRequests.WORK) -> ArniTheme.colors.info
                                    StatusRequests.parse(StatusRequests.DRAFT) -> ArniTheme.colors.error


                                    StatusRequests.parse(StatusRequests.COMPLETED) -> ArniTheme.colors.success
                                    else -> {
                                        ArniTheme.colors.white_100
                                    }
                                }, shape = RoundedCornerShape(15.dp)
                            )
                            .background(
                                color = when (isStatus) {
                                    StatusRequests.parse(StatusRequests.WORK) -> ArniTheme.colors.info.copy(alpha = 0.2f)
                                    StatusRequests.parse(StatusRequests.DRAFT) -> ArniTheme.colors.error.copy(alpha = 0.2f)
                                    StatusRequests.parse(StatusRequests.COMPLETED) -> ArniTheme.colors.success.copy(
                                        alpha = 0.2f
                                    )

                                    else -> {
                                        ArniTheme.colors.white_100
                                    }
                                }
                            )
                    ) {
                        Text(
                            text = when (isStatus) {
                                StatusRequests.parse(StatusRequests.WORK) -> "Рабочая"
                                StatusRequests.parse(StatusRequests.DRAFT) -> "Черновик"
                                StatusRequests.parse(StatusRequests.COMPLETED) -> "Завершена"
                                else -> {
                                    ""
                                }
                            }, style = ArniTheme.typography.subhead.bold, color =
                            when (isStatus) {
                                StatusRequests.parse(StatusRequests.WORK) -> ArniTheme.colors.info
                                StatusRequests.parse(StatusRequests.DRAFT) -> ArniTheme.colors.error
                                StatusRequests.parse(StatusRequests.COMPLETED) -> ArniTheme.colors.success
                                else -> {
                                    ArniTheme.colors.white_100
                                }
                            },
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp)
                        )
                    }
                    Column {
                        if (isStatusDelete)
                            Icon(
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(5.dp),
                                painter = painterResource(id = R.drawable.ic_delete_request),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = ArniTheme.colors.neutral_100)

                ) {
                    Text(
                        text = Urgency,
                        style = ArniTheme.typography.subhead.bold,
                        color = ArniTheme.colors.black_100,
                        modifier = Modifier.padding(top = 3.dp, start = 10.dp, bottom = 3.dp, end = 10.dp)
                    )
                }
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, end = 10.dp),
                    text = fromDepartment,
                    style = ArniTheme.typography.subhead.bold,
                    color = ArniTheme.colors.black_100,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
                Icon(
                    modifier = Modifier.padding(top = 8.dp),
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = ""
                )
                Text(
                    text = toDepartment,
                    style = ArniTheme.typography.subhead.bold,
                    color = ArniTheme.colors.black_100,
                    modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, start = 10.dp, end = 12.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row {
                    Text(
                        text = if (!nameExecutor.isNullOrEmpty()) nameExecutor else "Исполнитель не назначен",
                        style = ArniTheme.typography.subhead.regular,
                        color = if (nameExecutor.isNullOrEmpty()) ArniTheme.colors.black_100.copy(0.5f) else ArniTheme.colors.black_100,
                        modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, end = 12.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, widthDp = 500, heightDp = 1500)
private fun ItemEventPreview() {
    ArniTheme {
        Column(
            Modifier
                .height(1900.dp)
                .width(900.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ItemRequest("Отделение 1", "Отделение 2", "Иванов Иван", "", false, "П") {}
            ItemRequest("Отделение 1", "Отделение 2", "Иванов Иван", "", true, "Э") {}
            ItemRequest("Отделение 1", "Отделение 2", null, "", false, "П") {}
        }
    }
}
