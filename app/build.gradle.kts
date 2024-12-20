plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.appgrupo11"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appgrupo11"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.room.runtime.android)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    implementation("androidx.activity:activity-compose:1.6.0")
    implementation(libs.androidx.material.icons.extended)
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation(libs.androidx.navigation.compose)
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation(libs.firebase.firestore.ktx)
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //Firebase
    implementation(libs.firebase.firestore)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose UI
    implementation ("androidx.compose.ui:ui:1.5.0")
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0")


// Optional for Compose tooling
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.0")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.5.0")


    // Compose UI
    implementation ("androidx.compose.ui:ui:1.5.0")
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0")


// Optional for Compose tooling
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.0")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.5.0")

    // ViewModel support in Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

// Optional for Compose tooling
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.0")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.5.0")




}

kapt {
    correctErrorTypes = true
}