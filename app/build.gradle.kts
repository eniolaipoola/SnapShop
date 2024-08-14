import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

android {
    namespace = "com.tei.snapshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tei.snapshop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Set manifest placeholders
        manifestPlaceholders["facebookAppId"] = localProperties["facebookAppId"] as Any
        manifestPlaceholders["facebookClientId"] = localProperties["facebookClientId"] as Any
        manifestPlaceholders["facebookLoginProtocolScheme"] = localProperties["fbLoginProtocolScheme"] as Any


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    //Compose
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.navigation.compose)
    implementation(libs.google.accompanist.lib)

    //hilt
    implementation(libs.hilt)
    implementation(libs.hilt.compose.navigation)
    implementation(libs.androidx.junit.ktx)
    //androidTestImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    ksp(libs.hilt.android.compiler)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.square.retrofit.gson)
    implementation(libs.logging.interceptor)
    testImplementation(libs.okhttp.test)

    //logging
    implementation(libs.timber)

    //coil
    implementation(libs.coil.compose)

    implementation(libs.androidx.preference.ktx)

    implementation(libs.splashscreen)

    //firebase
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.facebook.login)
    implementation(libs.firebase.crashlytics)

    //Unit / Implementation test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.junit)
    // For Mockito
    testImplementation(libs.mockito.core)
    // For Mockito JUnit Runner
    testImplementation(libs.mockito.junit.jupiter)
    // For Android instrumentation tests with Mockito
    androidTestImplementation(libs.mockito.android)
}