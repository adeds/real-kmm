plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-android")
}
group = "id.id.adeds"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    // Koin
    implementation("org.koin:koin-android:2.2.0")
    implementation("org.koin:koin-core:2.2.0")
    implementation("org.koin:koin-androidx-viewmodel:2.2.0")

    // Ktor
    implementation("io.ktor:ktor-client-core:1.4.0")
    implementation("io.ktor:ktor-client-serialization:1.4.0")
    implementation("io.ktor:ktor-client-android:1.4.0")

    // Coil
    implementation("io.coil-kt:coil:1.1.1")

}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "id.id.adeds.androidapp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // For Kotlin projects
    kotlinOptions {
        jvmTarget = "1.8"
    }
}