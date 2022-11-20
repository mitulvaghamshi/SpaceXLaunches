package me.mitul.spacexlaunches.shared.dev

import me.mitul.spacexlaunches.entity.Links
import me.mitul.spacexlaunches.entity.Rocket
import me.mitul.spacexlaunches.entity.RocketLaunch

class SampleData {
    val falcon1 get() = getLaunch()

    fun getLaunches(length: Int = 1): List<RocketLaunch> {
        val launches = mutableListOf<RocketLaunch>()
        for (i in 1..length) {
            launches.add(getLaunch(flightNumber = i, isSuccess = i % 2 == 0))
        }
        return launches
    }

    private fun getLaunch(flightNumber: Int = 1, isSuccess: Boolean = false): RocketLaunch {
        return RocketLaunch(
            flightNumber = flightNumber,
            missionName = "FalconSat",
            launchYear = 2006,
            launchDateUTC = "2006-03-24T22:30:00.000Z",
            rocket = Rocket(id = "falcon1", name = "Falcon 1", type = "Merlin A"),
            details = "Falcon 1 was a small-lift launch vehicle that was operated from 2006 to 2009 by SpaceX, an American aerospace manufacturer. On 28 September 2008, Falcon 1 became the first privately-developed fully liquid-fueled launch vehicle to go into orbit around the Earth.",
            launchSuccess = isSuccess,
            links = Links(
                missionPatchUrl = "https://images2.imgbox.com/40/e3/GypSkayF_o.png",
                articleUrl = "https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html"
            )
        )
    }
}
