plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id 'kotlin-kapt'  // Añadido para procesar anotaciones de Room
}

android {
    namespace = "com.example.parcial"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.parcial"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Dependencias existentes
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Añadidas: Room y soporte para corutinas
    implementation "androidx.room:room-runtime:2.6.1"
    implementation "androidx.room:room-ktx:2.6.1"  // Para soporte de corutinas en Room
    kapt "androidx.room:room-compiler:2.6.1"       // Procesador de anotaciones para Room
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1"  // Corutinas para Kotlin
}