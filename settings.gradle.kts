pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
      //  maven { url 'https://jitpack.io' }

    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
       // maven { url 'https://www.jitpack.io' }
    }
}

rootProject.name = "Naari Rakshak"
include(":app")
