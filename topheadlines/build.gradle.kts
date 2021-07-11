plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 27
        targetSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-rc01"
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.bundles.compose)
    implementation("androidx.activity:activity-compose:1.3.0-rc01")

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    api(libs.bundles.retrofit)

    implementation(libs.bundles.kotlinx.coroutines)
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.8")

    testImplementation(libs.junit.core)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)

    api(project(":mvi"))
    api(project(":shared"))
}