package me.mitul.spacexlaunches

class AndroidPlatform : Platform {
    override val name: String = "${android.os.Build.VERSION.BASE_OS} ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
