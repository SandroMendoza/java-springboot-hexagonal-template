plugins {
    java
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// https://docs.gradle.org/current/userguide/dynamic_versions.html#sub:declaring_dependency_with_changing_version
configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

buildscript {
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    }
}

tasks.test {
    useJUnitPlatform()
}
