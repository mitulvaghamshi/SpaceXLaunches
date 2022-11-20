package me.mitul.spacexlaunches.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import me.mitul.spacexlaunches.android.ui.AppTheme
import me.mitul.spacexlaunches.android.ui.LaunchList
import me.mitul.spacexlaunches.entity.RocketLaunch
import me.mitul.spacexlaunches.shared.SpaceXSDK
import me.mitul.spacexlaunches.shared.cache.DatabaseDriverFactory

class MainActivity : ComponentActivity() {
    private val sdk = SpaceXSDK(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            var launches by remember { mutableStateOf(listOf<RocketLaunch>()) }

            suspend fun loadLaunches(forceReload: Boolean = false) {
                if (launches.isEmpty()) {
                    scope.launch {
                        launches = try {
                            sdk.getLaunches(forceReload)
                        } catch (e: Exception) {
                            throw Exception(e.localizedMessage)
                        }
                    }
                }
            }

            suspend fun onRefresh() {
                launches = listOf()
                loadLaunches(forceReload = true)
            }

            fun onRemove(launch: RocketLaunch) {
                val newList = launches.dropWhile { it.flightNumber == launch.flightNumber }
                launches = newList
            }

            LaunchedEffect(Unit) { loadLaunches() }

            AppTheme {
                LaunchList(launches, { onRemove(it) }) {
                    scope.launch { onRefresh() }
                }
            }
        }
    }
}
