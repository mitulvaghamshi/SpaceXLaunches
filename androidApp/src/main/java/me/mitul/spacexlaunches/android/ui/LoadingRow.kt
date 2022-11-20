package me.mitul.spacexlaunches.android.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Shows the loading state of the weather.
 */
@Composable
fun LoadingRow() {
    // Creates an `InfiniteTransition` that runs infinite child animation values.
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        // `infiniteRepeatable` repeats the specified
        // duration-based `AnimationSpec` infinitely.
        animationSpec = infiniteRepeatable(
            // The `keyframes` animates the value by specifying multiple timestamps.
            animation = keyframes {
                // One iteration is 1000 milliseconds.
                durationMillis = 1000
                // 0.7f at the middle of an iteration.
                0.7f at 500
            },
            // When the value finishes animating from 0f to 1f,
            // it repeats by reversing the animation direction.
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        val shimmerColor = Color.DarkGray.copy(alpha)
        Box(
            Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(shimmerColor)
        )
        Spacer(Modifier.width(16.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(shimmerColor)
        )
    }
}

@Preview
@Composable
fun PreviewLoadingRow() = Surface {
    LoadingRow()
}
