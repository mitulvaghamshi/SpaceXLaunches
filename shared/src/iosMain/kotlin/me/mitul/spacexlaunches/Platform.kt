package me.mitul.spacexlaunches

import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    private val device = UIDevice.currentDevice
    override val name: String = "${device.systemName()} ${device.systemVersion}"
}

actual fun getPlatform(): Platform = IOSPlatform()
