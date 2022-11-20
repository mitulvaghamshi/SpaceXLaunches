package me.mitul.spacexlaunches

import kotlinx.datetime.*

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

class Greeting {
    private val platform: Platform = getPlatform()

    private fun getDays(): Int {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val closestNewYear = LocalDate(today.year + 1, 1, 1)
        return today.daysUntil(closestNewYear)
    }

    fun greeting(): String {
        return "App is running on: ${platform.name}!\nThere are only ${getDays()} left until New Year!"
    }
}
