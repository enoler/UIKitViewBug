plugins {
    id("org.jetbrains.compose").version("1.5.10").apply(false)
}

buildscript {
    val androidGradle = findProperty("agp.version")
    val kotlin = findProperty("kotlin.version")
    val mokoResources = findProperty("moko.resources.version")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$androidGradle")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
        classpath("dev.icerock.moko:resources-generator:$mokoResources")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.google.com")
        maven(url = "https://jitpack.io")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
    repositories {
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}