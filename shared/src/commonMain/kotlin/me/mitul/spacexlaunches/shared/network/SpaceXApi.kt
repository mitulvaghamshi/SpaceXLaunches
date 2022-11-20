package me.mitul.spacexlaunches.shared.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.mitul.spacexlaunches.entity.RocketLaunch
import me.mitul.spacexlaunches.shared.dev.SampleData

class SpaceXApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = true
            })
        }
    }

    suspend fun getAllLaunches(isDev: Boolean): List<RocketLaunch> {
        if (isDev) return SampleData().getLaunches(30)
        return httpClient
            .get("https://api.spacexdata.com/v3/launches")
            .body()
    }
}
