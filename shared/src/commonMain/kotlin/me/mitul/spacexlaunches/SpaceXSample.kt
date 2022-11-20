package me.mitul.spacexlaunches

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class SpaceXSample {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Throws(Exception::class)
    suspend fun launchedRockets(): List<Rocket> {
        return httpClient
            .get("https://api.spacexdata.com/v4/launches")
            .body()
    }
}

@Serializable
data class Rocket(
    @SerialName("flight_number") val flightNumber: Int,
    @SerialName("name") val missionName: String,
    @SerialName("date_utc") val launchDateUTC: String,
    @SerialName("success") val launchSuccess: Boolean?,
)
