package me.mitul.spacexlaunches.android.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.mitul.spacexlaunches.entity.RocketLaunch
import me.mitul.spacexlaunches.shared.dev.SampleData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Shows a row for one topic.
 *
 * @param launch The topic title.
 * @param expanded Whether the row should be shown expanded with the topic body.
 * @param onClick Called when the row is clicked.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchRow(
    launch: RocketLaunch,
    expanded: Boolean,
    onDismissed: () -> Unit,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val articleIntent = launch.links.articleUrl?.let {
        remember { Intent(Intent.ACTION_VIEW, Uri.parse(it)) }
    }
    val patchIntent = launch.links.missionPatchUrl?.let {
        remember { Intent(Intent.ACTION_VIEW, Uri.parse(it)) }
    }

    LaunchRowSpacer(expanded)
    Surface(onClick,
        Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp), elevation = 2.dp) {
        Column(Modifier.padding(16.dp)) {
            Row {
                Icon(Icons.Default.RocketLaunch, "Rocket Icon")
                Spacer(Modifier.width(16.dp))
                Text(launch.missionName, style = MaterialTheme.typography.body1)
            }
            if (expanded) {
                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), SpaceBetween, CenterVertically) {
                    launch.launchSuccess?.let {
                        Text(
                            if (it) "Successful" else "Unsuccessful",
                            color = if (it) Color.Green else Color.Red,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row {
                        articleIntent?.let {
                            TextButton({ context.startActivity(it) }) { Text("Article") }
                        }
                        patchIntent?.let {
                            Spacer(Modifier.width(10.dp))
                            TextButton({ context.startActivity(it) }) { Text("Patch") }
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = launch.launchDateUTC.formatted(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                launch.details?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = it,
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                    )
                }
            }
        }
    }
    LaunchRowSpacer(expanded)
}

private fun String.formatted(): String {
    val date = this.replace("Z", "")

    val fromPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ".replace("Z", "")
    val toPattern = "LLL dd, yyyy 'at' hh:mm a"

    val utcFormat = DateTimeFormatter.ofPattern(fromPattern)
    val isoFormat = DateTimeFormatter.ofPattern(toPattern)

    return LocalDateTime.parse(date, utcFormat).format(isoFormat)
}

/**
 * Shows a separator for topics.
 */
@Composable
private fun LaunchRowSpacer(visible: Boolean) {
    AnimatedVisibility(visible) {
        Spacer(Modifier.height(8.dp))
    }
}

@Preview
@Composable
private fun PreviewLaunchRow() {
    val falcon1 = SampleData().falcon1
    LaunchRow(falcon1, false, {}, {})
}

@Preview
@Composable
private fun PreviewLaunchRowExpanded() {
    val falcon1 = SampleData().falcon1
    LaunchRow(falcon1, true, {}, {})
}
