package me.mitul.spacexlaunches.android.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import me.mitul.spacexlaunches.entity.RocketLaunch
import me.mitul.spacexlaunches.shared.dev.SampleData

/**
 * Shows the entire screen.
 */
@Composable
fun LaunchList(
    launches: List<RocketLaunch>,
    onRemove: (RocketLaunch) -> Unit,
    onRefresh: () -> Unit,
) {
    var expandedItem by remember { mutableStateOf<Int?>(null) }
    val lazyListState = rememberLazyListState()
    var darkMode by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (darkMode) Green200 else Red200)

    Scaffold(
        backgroundColor = backgroundColor,
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = backgroundColor,
                title = { Text("SpaceX Flights") },
                actions = {
                    IconButton(onRefresh) {
                        Icon(Icons.Filled.Refresh, "Reload Button")
                    }
                    IconButton({ darkMode = !darkMode }) {
                        if (darkMode) {
                            Icon(Icons.Default.LightMode, "Light Theme Button")
                        } else {
                            Icon(Icons.Default.DarkMode, "Dark Theme Button")
                        }
                    }
                }
            )
        }
    ) {
        val contentPadding = PaddingValues(
            top = it.calculateTopPadding() + 8.dp,
            bottom = it.calculateBottomPadding() + 20.dp,
            start = it.calculateLeftPadding(LayoutDirection.Ltr) + 16.dp,
            end = it.calculateRightPadding(LayoutDirection.Ltr) + 16.dp,
        )
        LazyColumn(contentPadding = contentPadding, state = lazyListState) {
            item {
                Text(
                    text = "All Rocket Launches",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.semantics { heading() },
                )
            }
            item { Spacer(Modifier.height(16.dp)) }
            if (launches.isEmpty()) {
                items(15) { LoadingRow() }
            } else {
                items(launches) { launch ->
                    key(launch) {
                        val expanded = expandedItem == launch.flightNumber
                        LaunchRow(launch, expanded, { onRemove(launch) }) {
                            expandedItem = if (expanded) null else launch.flightNumber
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAnimations() {
    LaunchList(SampleData().getLaunches(15), {}, {})
}
