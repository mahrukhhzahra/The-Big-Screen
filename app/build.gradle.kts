import java.io.FileInputStream
import java.util.Properties

plugins {
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
//    id("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"

}

val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))


fun apiKey(): String {
    return "722180da59ab66d43ae332fb92be98bd"
}

android {
    namespace = "com.example.thebigscreen"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.thebigscreen"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }


//        buildConfigField("String", "YOUR_API_KEY", apikeyProperties["YOUR_API_KEY"].toString())
        buildConfigField("String", "TMDB_API_KEY", apiKey())
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    // OK HTTP 3
    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")


    
    // Paging 3
    implementation("androidx.paging:paging-runtime:3.2.1")
    implementation("androidx.paging:paging-compose:3.2.1")

    // Retrofit
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")


//    // Navigation
//    implementation("androidx.navigation:navigation-compose:2.7.2")
//
    // Room
    implementation("androidx.room:room-runtime:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("androidx.room:room-paging:2.6.0")
//
//    // Coil
//    implementation("io.coil-kt:coil-compose:2.4.0")
//
//    // LiveData
//    implementation("androidx.compose.runtime:runtime-livedata:1.5.1")
//
//    // ViewModel utilities for Compose
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
//    // Saved state module for ViewModel
//    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$2.6.2")
//
//    // kotlin serialization
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
//    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.3")








    // Material Icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // Constraint Layout
    val constraintlayoutVersion = "1.1.1"
//    implementation("androidx.constraintlayout:constraintlayout-compose:$constraintlayoutVersion")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.5-alpha")

    // Landscapist image loader
    val landscapistVersion = "1.6.1"
    implementation("com.github.skydoves:landscapist-coil:$landscapistVersion")

    // RatingBar
//    implementation("com.github.a914-gowtham:compose-ratingbar:1.2.3")
//    implementation("com.github.SmartToolFactory:Compose-RatingBar:Tag")
    implementation("com.github.a914-gowtham:compose-ratingbar:1.3.4")

    // Timber
    val timberVersion = "5.0.1"
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // Stetho
    implementation("com.facebook.stetho:stetho:1.6.0")
    implementation("com.facebook.stetho:stetho-okhttp3:1.6.0")

    // Lottie
//    val lottieVersion = "6.0.0-beta.1"
//    implementation("com.airbnb.android:lottie-compose:$lottieVersion")
    val lottieVersion = "6.1.0"
    implementation("com.airbnb.android:lottie-compose:$lottieVersion")


    // RaamCosta Navigation
    implementation("io.github.raamcosta.compose-destinations:core:1.4.0-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.4.0-beta")



    // Hilt - for @HiltViewModel
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Prefs Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")



    val lifecycle_version = "2.6.2"
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")



/////////////////////////////////////////////////////////
//    // Hilt
//    val hiltVersion = "2.44"
//    implementation("com.google.dagger:hilt-android:$hiltVersion")
//    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt("androidx.hilt:hilt-compiler:1.0.0")
//
//    // Hilt - for @HiltViewModel
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
//
//    // Retrofit
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
//    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
//
//    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
//
//    // Coroutine Lifecycle Scopes
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
////    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
//
//    // Pagination
//    implementation("androidx.paging:paging-compose:3.3.0-alpha02")
//
//    //Prefs Datastore
//    implementation("androidx.datastore:datastore-preferences:1.0.0")
//
//    // Room
//    val room_version = "2.6.0"
//    implementation("androidx.room:room-runtime:$room_version")
//    implementation("androidx.room:room-ktx:$room_version")
//    kapt("androidx.room:room-compiler:$room_version")

}


//
//
//plugins {
//    id 'com.android.application'
//    id 'org.jetbrains.kotlin.android'
//    id 'kotlin-kapt'
//    id 'kotlin-parcelize'
//    id 'dagger.hilt.android.plugin'
//    id 'com.google.devtools.ksp' version '1.6.10-1.0.2'
//}
//
//def apikeyPropertiesFile = rootProject.file("apikey.properties")
//def apikeyProperties = new Properties()
//apikeyProperties.load(new FileInputStream(apikeyPropertiesFile))
//
//android {
//
////    buildFeatures {
////        incremental = false
////    }
//
//    namespace "com.example.thebigscreen"
//    compileSdk = 33
//
//    defaultConfig {
//        applicationId = "com.example.thebigscreen"
//        minSdk = 26
//        targetSdk = 33
//        versionCode = 4
//        versionName = "v1.0-rc"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary = true
//        }
//
//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments += ["room.schemaLocation": "$projectDir/schemas".toString()]
//            }
//        }
//
//        buildConfigField("String", "YOUR_API_KEY", apikeyProperties['YOUR_API_KEY'])
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled = true
//            shrinkResources = true
//            proguardFiles getDefaultProguardFile(
//                    "proguard-android-optimize.txt");
//            "proguard-rules.pro"
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    buildFeatures {
//        compose = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion compose_version
//    }
//    packagingOptions {
//        resources {
//            excludes += '/META-INF/{AL2.0,LGPL2.1}'
//        }
//    }
//    kapt {
//        correctErrorTypes = true
//    }
//}
//kotlin {
//    sourceSets {
//        debug {
//            kotlin.srcDir("build/generated/ksp/debug/kotlin")
//        }
//        release {
//            kotlin.srcDir("build/generated/ksp/release/kotlin")
//        }
//    }
//}
//
//dependencies {
//    implementation("androidx.core:core-ktx:1.8.0")
//    implementation("androidx.compose.ui:ui:1.3.0-alpha01")
//    implementation("androidx.compose.material:material:$compose_version")
//    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")
//    implementation("androidx.activity:activity-compose:1.5.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
//    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
//
//    // Material Icons
//    implementation("androidx.compose.material:material-icons-extended:1.1.1")
//
//    // Constraint Layout
//    implementation("androidx.constraintlayout:constraintlayout-compose:$constraintlayoutVersion")
//
//    // Accompanist
//    implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.5-alpha")
//
//    // Landscapist image loader
//    implementation("com.github.skydoves:landscapist-coil:$landscapistVersion")
//
//    // RatingBar
//    implementation("com.github.a914-gowtham:compose-ratingbar:1.2.3")
//
//    // Timber
//    implementation("com.jakewharton.timber:timber:$timberVersion")
//
//    // Stetho
//    implementation("com.facebook.stetho:stetho:1.6.0")
//    implementation("com.facebook.stetho:stetho-okhttp3:1.6.0")
//
//    // Lottie
//    implementation("com.airbnb.android:lottie-compose:$lottieVersion")
//
//    // RaamCosta Navigation
//    implementation("io.github.raamcosta.compose-destinations:core:1.4.0-beta")
//    ksp("io.github.raamcosta.compose-destinations:ksp:1.4.0-beta")
//
//    // Hilt
//    implementation("com.google.dagger:hilt-android:$hiltVersion")
//    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt("androidx.hilt:hilt-compiler:1.0.0")
//
//    // Hilt - for @HiltViewModel
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
//
//    // Retrofit
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
//    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
//
//    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
//
//    // Coroutine Lifecycle Scopes
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")
//
//    // Pagination
//    implementation("androidx.paging:paging-compose:1.0.0-alpha15")
//
//    //Prefs Datastore
//    implementation("androidx.datastore:datastore-preferences:1.0.0")
//
//    // Room
//    def room_version = "2.4.2"
//    implementation("androidx.room:room-runtime:$room_version")
//    implementation("androidx.room:room-ktx:$room_version")
//    kapt("androidx.room:room-compiler:$room_version")
//}