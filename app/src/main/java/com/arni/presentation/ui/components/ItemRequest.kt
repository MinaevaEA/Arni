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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.enum.StatusRequests
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun ItemEvent(
    fromDepartment: String,
    toDepartment: String,
    nameExecutor: String?,
    isStatus: Int,
    Urgency: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = ArniTheme.colors.black_100,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(shape = RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .background(color = ArniTheme.colors.white_100)
            .padding(16.dp)

    ) {
        Column(
            modifier = Modifier
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(
                    modifier = Modifier
                        .padding(end = 4.dp)
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
                                StatusRequests.parse(StatusRequests.COMPLETED) -> ArniTheme.colors.success.copy(alpha = 0.2f)
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
                        }, style = ArniTheme.typography.body.bold, color =
                        when (isStatus) {
                            StatusRequests.parse(StatusRequests.WORK) -> ArniTheme.colors.info
                            StatusRequests.parse(StatusRequests.DRAFT) -> ArniTheme.colors.error
                            StatusRequests.parse(StatusRequests.COMPLETED) -> ArniTheme.colors.success
                            else -> {
                                ArniTheme.colors.white_100
                            }
                        },
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = ArniTheme.colors.neutral_100)

                ) {
                    Text(
                        text = Urgency,
                        style = ArniTheme.typography.body.semibold,
                        color = ArniTheme.colors.black_100,
                        modifier = Modifier.padding(top = 6.dp, start = 12.dp, bottom = 6.dp, end = 12.dp)
                    )
                }
            }
            Row {
                Text(
                    text = fromDepartment,
                    style = ArniTheme.typography.body.semibold,
                    color = ArniTheme.colors.black_100,
                    modifier = Modifier.padding(top = 12.dp, bottom = 6.dp, end = 12.dp)
                )
                Icon(modifier = Modifier.padding(top = 18.dp),painter = painterResource(id = R.drawable.ic_next), contentDescription = "")
                    Text(
                        text = toDepartment,
                        style = ArniTheme.typography.body.semibold,
                        color = ArniTheme.colors.black_100,
                        modifier = Modifier.padding(top = 12.dp, bottom = 6.dp, start = 12.dp, end = 12.dp)
                    )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row {
                    Text(
                        text = nameExecutor ?: "Исполнитель не назначен",
                        style = ArniTheme.typography.body.regular,
                        color = if (nameExecutor == null) ArniTheme.colors.black_100.copy(0.5f) else ArniTheme.colors.black_100,
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp, end = 12.dp)
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
        Row {
            Column(
                Modifier
                    .padding(top = 24.dp)
                    .height(1900.dp)
                    .width(900.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ItemEvent("Отделение 1", "Отделение 2", "Иванов Иван", 1, "П") {}
                ItemEvent("Отделение 1", "Отделение 2", "Иванов Иван", 2, "Э") {}
                ItemEvent("Отделение 1", "Отделение 2", null, 3, "П") {}
            }
        }
    }
}
