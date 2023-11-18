@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization").version("1.9.0")
    id("dev.icerock.mobile.multiplatform-resources")
    id("org.jetbrains.compose")
}

group = "uikitview.bug"
version = findProperty("android.versionName") as String

kotlin {
    androidTarget()

    val mokoResourcesVersion = findProperty("moko.resources.version") as String
    val mokoMvvmVersion = findProperty("moko.mvvm.version") as String
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export("dev.icerock.moko:resources:$mokoResourcesVersion")
            export("dev.icerock.moko:mvvm-livedata:$mokoMvvmVersion")
        }
    }

    sourceSets {
        val koinVersion = findProperty("koin.version") as String

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:atomicfu:0.20.2")

                implementation(compose.runtime)
                implementation(compose.foundation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(compose.material3)

                implementation("co.touchlab:kermit:1.2.2")
                implementation("dev.icerock.moko:mvvm-livedata-compose:0.16.1")

                implementation("io.insert-koin:koin-core:$koinVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.6")
            }
        }
        val commonTest by getting {}
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.insert-koin:koin-android:$koinVersion")
            }
        }
        val androidUnitTest by getting {
            dependsOn(commonTest)
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

dependencies {
    val mokoResourcesVersion = findProperty("moko.resources.version") as String
    val mokoMvvmVersion = findProperty("moko.mvvm.version") as String

    commonMainApi("dev.icerock.moko:resources:$mokoResourcesVersion")
    commonMainApi("dev.icerock.moko:resources-compose:$mokoResourcesVersion")
    commonMainApi("dev.icerock.moko:mvvm-livedata-compose:$mokoMvvmVersion")

    commonTestImplementation("dev.icerock.moko:resources-test:$mokoResourcesVersion")
}

multiplatformResources {
    multiplatformResourcesPackage = "uikitview.bug"
    disableStaticFrameworkWarning = true
    iosBaseLocalizationRegion = "en"
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "uikitview.bug.shared"
    testOptions {
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}