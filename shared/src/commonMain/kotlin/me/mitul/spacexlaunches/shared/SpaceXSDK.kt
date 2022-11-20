package me.mitul.spacexlaunches.shared

import me.mitul.spacexlaunches.entity.RocketLaunch
import me.mitul.spacexlaunches.shared.cache.Database
import me.mitul.spacexlaunches.shared.cache.DatabaseDriverFactory
import me.mitul.spacexlaunches.shared.network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean, isDev: Boolean = false): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches(isDev).also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}
