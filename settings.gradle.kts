//pluginManagement {
//    repositories {
//        google()
//        mavenCentral()
//        gradlePluginPortal()
//    }
//}
//dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//    repositories {
//        google()
//        mavenCentral()
//    }
//}
//
//rootProject.name = "TheBigScreen"
//include(":app")
//

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (url  = "https://jitpack.io")
        maven (url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "TheBigScreen"
include(":app")
