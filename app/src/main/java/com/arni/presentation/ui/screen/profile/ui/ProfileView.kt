package com.arni.presentation.ui.screen.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arni.R
import com.arni.presentation.ui.components.ButtonFillLarge
import com.arni.presentation.ui.components.TextFieldInput
import com.arni.presentation.ui.components.TextTitleToolbar
import com.arni.presentation.ui.screen.signIn.ui.SignInEvent
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun ProfileView(
    state: ProfileState,
    eventConsumer: (ProfileEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArniTheme.colors.neutral_0)
            .padding(16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextTitleToolbar(title = "Профиль")
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            /*AsyncImage(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(12.dp))
                    .size(40.dp)
                    .background(Color.Transparent, shape = RoundedCornerShape(12.dp)),
                model = "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png",
                contentDescription = "https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png",
                alignment = Alignment.CenterStart
            )*/
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .clickable {}
                    .background(shape = RoundedCornerShape(24.dp), color = ArniTheme.colors.neutral_300)
                    .height(120.dp)
                    .width(120.dp)
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
        }
        TextFieldInput(
            modifier = Modifier.padding(top = 20.dp),
            label = stringResource(R.string.name_profile),
            text = "Петров Петр",
            placeholder = "",
            onValueChange = {  },
            error = "",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            onEndIconClick = { }
        )
        TextFieldInput(
            modifier = Modifier.padding(top = 10.dp),
            label = stringResource(R.string.name_profession),
            text = "Врач - терапевт",
            placeholder = "",
            onValueChange = { },
            error = "",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            onEndIconClick = { }
        )
        ButtonFillLarge(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            onClick = {  },
            text = stringResource(R.string.signOut_action),
            isEnabled = true
        )
    }
}
@Composable
@Preview
private fun ProfilePreview() {
    ArniTheme {
        ProfileView(ProfileState("")){}
    }
}
