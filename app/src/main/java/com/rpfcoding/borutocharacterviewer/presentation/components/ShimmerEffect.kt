package com.rpfcoding.borutocharacterviewer.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rpfcoding.borutocharacterviewer.ui.theme.*

@Composable
fun ShimmerEffect() {

    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(space = SMALL_PADDING)
    ) {
        items(count = 2) {
            AnimatedShimmerItem()
        }
    }

}

@Composable
fun AnimatedShimmerItem() {

    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = HERO_ITEM_HEIGHT),
        color = if(isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {
        Column(
            modifier = Modifier
                .padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth(0.8f)
                    .height(NAME_PLACEHOLDER_HEIGHT),
                color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            for(i in 5 downTo 1) {
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth((i * 0.2f))
                        .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
            }
//            Row(modifier = Modifier.fillMaxWidth()) {
//                repeat(5) {
//                    Surface(
//                        modifier = Modifier
//                            .alpha(alpha = alpha)
//                            .size(RATING_PLACEHOLDER_HEIGHT),
//                        color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
//                        shape = RoundedCornerShape(size = EXTRA_SMALL_PADDING)
//                    ) {}
//                    Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
//                }
//            }
        }
    }
}

@Preview
@Composable
fun AnimatedShimmerItemPreview() {
    AnimatedShimmerItem()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimatedShimmerItemDarkPreview() {
    AnimatedShimmerItem()
}