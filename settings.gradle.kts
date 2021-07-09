dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "kai"
include(":app")
include(":topheadlines")

enableFeaturePreview("VERSION_CATALOGS")
include(":mvi")
include(":shared")
