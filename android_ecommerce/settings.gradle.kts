pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Abrova packages are published on Abrova Center.
        maven { url = uri("https://center.abrova.ir/api/packages/abrova/maven") }
    }
}

rootProject.name = "AbrovaChat E-commerce Example"
include(":app")
