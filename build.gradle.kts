plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.projects"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

val isSpringInfraModule: (Project) -> Boolean = {
    it.path.startsWith(":adapters") ||
    it.path.startsWith(":core")
}

configure(subprojects.filter(isSpringInfraModule)) {
    apply(plugin = "io.spring.dependency-management")

    dependencyManagement {
        imports { mavenBom("org.springframework.boot:spring-boot-dependencies:3.2.5") }
    }
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation(project(":core"))
	implementation(project(":adapters:persistence"))
	implementation(project(":adapters:web-api-springboot"))
}

tasks.withType<Test> {
	useJUnitPlatform()
}
