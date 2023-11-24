// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id("com.android.application") version "8.1.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
//    id("com.google.dagger.hilt.android") version "2.44" apply false
//}


//buildscript {
//    ext {
//        compose_version = '1.1.1'
//        lottieVersion = "5.0.3"
//        timberVersion = "5.0.1"
//        ratingBarVersion = "1.2.3"
//        landscapistVersion = "1.4.9"
//        constraintlayoutVersion = "1.0.1"
//        hiltVersion = "2.41"
//    }
//    dependencies {
//        classpath('com.google.dagger:hilt-android-gradle-plugin:2.44')
//    }
//}
//plugins {
//    id 'com.android.application' version '7.2.1' apply false
//    id 'com.android.library' version '7.2.1' apply false
//    id 'org.jetbrains.kotlin.android' version '1.6.10' apply false
//}
//
//tasks.register('clean', Delete) {
//    delete rootProject.buildDir
//}



buildscript {
//    ext {
//        compose_version = "1.3.0"
//        lottieVersion = "6.0.0-beta.1"
//        timberVersion = "5.0.1"
//        ratingBarVersion = "1.2.3"
//        landscapistVersion = "1.6.1"
//        constraintlayoutVersion = "1.1.1"
//        hiltVersion = "2.5.0-alpha01"
//    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath("com.google.gms:google-services:4.4.0")
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
//    id("com.android.application") version "7.2.1" apply false
//    id("com.android.library") version "7.2.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.6.10" apply false


    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.0" apply false


}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
