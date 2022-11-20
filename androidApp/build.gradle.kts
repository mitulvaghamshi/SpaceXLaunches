plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "me.mitul.spacexlaunches.android"
    compileSdk = 33
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.3.0"
    buildTypes.getByName("release").isMinifyEnabled = false
    packagingOptions.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

    defaultConfig {
        applicationId = "me.mitul.spacexlaunches.android"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    val compose = "1.3.1"
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.compose.ui:ui-tooling:$compose")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose")
    implementation("androidx.compose.foundation:foundation:$compose")
    implementation("androidx.compose.material:material:$compose")
    implementation("androidx.compose.material:material-icons-extended:$compose")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=org.mylibrary.OptInAnnotation"
}
