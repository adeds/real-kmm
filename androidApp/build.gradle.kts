group = "id.adeds"
version = "1.0-SNAPSHOT"

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.activity:activity-compose:1.3.0-alpha04")
    implementation("androidx.appcompat:appcompat:1.3.0-beta01")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.ui:ui-graphics:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    implementation("androidx.compose.foundation:foundation-layout:${Versions.compose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
    implementation("androidx.navigation:navigation-compose:1.0.0-alpha09")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-alpha06")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
}

android {
    compileSdkVersion(Versions.compile_sdk)
    buildToolsVersion(Versions.build_tools)

    defaultConfig {
        applicationId = "id.adeds.androidapp"
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    // packagingOptions {
    //    exclude("META-INF/*.kotlin_module")
    //}
    buildTypes {
        getByName("release") {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type.
            isMinifyEnabled = true
            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true
            // Includes the default ProGuard rules files that are packaged with
            // the Android Gradle plugin. To learn more, go to the section about
            // R8 configuration files.
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    lint {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
        kotlinCompilerVersion = "1.4.30"
    }
}

kotlin.sourceSets.all {
    languageSettings.apply {
        useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
        useExperimentalAnnotation("androidx.compose.foundation.ExperimentalFoundationApi")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xskip-prerelease-check", "-Xskip-metadata-version-check")
    }
}