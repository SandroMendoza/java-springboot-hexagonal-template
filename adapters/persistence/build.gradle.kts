plugins {
    java
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(project(":core"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
