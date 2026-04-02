pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "java-springboot-hexagonal-template"

include("core")
include("adapters:web-api-springboot")
include("adapters:persistence")