buildscript {
    val kotlinVersion by extra("1.4.21")

    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.2")
    }
}
group = "id.id.adeds"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
