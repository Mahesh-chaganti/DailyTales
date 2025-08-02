plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.mahesh.storyaday"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mahesh.storyaday"
        minSdk = 24
        targetSdk = 35
        versionCode = 3
        versionName = "1.2"

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
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.cupertino)
    implementation(libs.cupertino.native)
    implementation(libs.cupertino.adaptive)
    implementation(libs.cupertino.decompose)
    implementation(libs.cupertino.icons.extended)
    //hilt
    implementation("com.google.dagger:hilt-android:2.55")
    kapt("com.google.dagger:hilt-android-compiler:2.55")
    kapt( libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    //okhttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)



//    implementation(files("libs/gson-2.2.4.jar"))

    //lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)


    // ... other dependencies
    implementation(libs.material3)
    implementation(libs.kotlinx.serialization.json)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //MoshiConverter
    implementation(libs.moshi.kotlin)
    // ... other dependencies

    //coil
    implementation(libs.coil.compose)

    //palette API
    implementation(libs.androidx.palette.ktx)


    //bottomnav
    implementation(libs.androidx.material)

    //datastore
    implementation(libs.androidx.datastore.preferences)

    //accompanist
    implementation(libs.accompanist.permissions)
    //room
    kapt("androidx.room:room-compiler:2.7.2")

    implementation(libs.androidx.room.runtime)
    implementation(libs.play.services.ads)


}