plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("plugin.serialization").version("1.9.0")
    id("dev.icerock.mobile.multiplatform-resources")
    id("org.jetbrains.compose")
}

android {
    val kotlinCompilerExtVersion = findProperty("kotlin.compose.compiler.extensions.version") as String

    buildFeatures {
        viewBinding = true
        dataBinding = false
        compose = true
    }
    compileSdk =  (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        applicationId = "uikitview.bug"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = (findProperty("android.versionCode") as String).toInt()
        versionName = findProperty("android.versionName") as String
        renderscriptTargetApi = 25
        renderscriptSupportModeEnabled = true
        multiDexEnabled = true
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        kotlin {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
            }
        }
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    namespace = "uikitview.bug"
    composeOptions {
        kotlinCompilerExtensionVersion = kotlinCompilerExtVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "uikitview.bug"
}

dependencies {
    val koinAndroidVersion = findProperty("koin.version") as String
    val mokoResourcesVersion = findProperty("moko.resources.version") as String
    val mokoMvvmVersion = findProperty("moko.mvvm.version") as String

    implementation(project(":shared"))
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")

    implementation(compose.runtime)
    implementation(compose.foundation)
    @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
    implementation(compose.components.resources)
    implementation(compose.material3)
    implementation(compose.preview)

    implementation("com.google.android.material:material:1.10.0")
    implementation("com.google.android.play:core-ktx:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.6")
    implementation("dev.icerock.moko:resources:$mokoResourcesVersion")
    implementation("dev.icerock.moko:mvvm-livedata-compose:$mokoMvvmVersion")

    implementation("io.coil-kt:coil:2.5.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("io.insert-koin:koin-core:$koinAndroidVersion")
    implementation("io.insert-koin:koin-android:$koinAndroidVersion")
    implementation("io.insert-koin:koin-test:$koinAndroidVersion")
}