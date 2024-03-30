package com.arni.presentation.ui.screen.state_machine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.arni.R
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun LoadingStateView() {

    val compositionViolet by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.violet_green)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LottieAnimation(
            modifier = Modifier
                .size(82.dp)
                .align(Alignment.Center),
            composition = compositionViolet,
            contentScale = ContentScale.Crop,
            iterations = LottieConstants.IterateForever
        )
    }
}
@Composable
@Preview
private fun LoaderStateViewPreview() {
    ArniTheme {
        LoadingStateView()
    }
}
