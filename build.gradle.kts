import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerInspectContainer
import com.bmuschko.gradle.docker.tasks.container.DockerKillContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerPullImage
import com.github.dockerjava.api.command.InspectContainerResponse
import com.github.dockerjava.api.exception.NotFoundException
import org.gradle.api.Action

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
	id("com.bmuschko.docker-remote-api") version "9.4.0"
}

group = "ru.projektio"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}
dependencies {
	implementation("io.jsonwebtoken:jjwt-api:0.12.3")
	implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
	implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.mockito:mockito-core:3.12.4")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.postgresql:postgresql:42.7.2")
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("startDevPostgres") {
	group = "docker"
	doLast {
		println("starting dev postgres")
	}
}

val dockerContainerName: String = "local-database-postgres"

tasks.register<DockerPullImage>("pullPostgresImage") {
	group = "docker"
	image.set("postgres:latest")
}

tasks.register<DockerCreateContainer>("createPostgresContainer") {
	dependsOn("pullPostgresImage")
	group = "docker"
	imageId.set("postgres:latest")
	containerName.set(dockerContainerName)
	envVars.set(mapOf(
		"POSTGRES_DB" to "KFD",
		"POSTGRES_USER" to "user",
		"POSTGRES_PASSWORD" to "pass"
	))
	hostConfig.portBindings.set(listOf("5432:5432"))
}

tasks.register<DockerStopContainer>("stopDockerContainer") {
	group = "docker"
	containerId.set(dockerContainerName)
}

tasks.register<DockerStartContainer>("Build And Run Container") {
	dependsOn("createPostgresContainer")
	group = "docker"
	containerId.set(dockerContainerName)
}

tasks.register<DockerStartContainer>("Run Builded Container") {
	group = "docker"
	containerId.set(dockerContainerName)
}

tasks.register("startLocalPostgres") {
	group = "docker"
	dependsOn("startPostgresContainer")
}

tasks.register("bootRunDev") {
	group = "application"
//	dependsOn("startLocalPostgres")
	doLast {
		exec {
			commandLine("./gradlew", "bootRun", "-Dspring.profiles.active=dev", "--continue")
		}
	}
}

tasks.register("stopPostgresContainer") {
	group = "docker"
	dependsOn("stopDockerContainer")
	doLast {
		println("Stopped PostgreSQL container")
	}
}

tasks.register("bootRunProd") {
	group = "application"
	doLast {
		exec {
			commandLine("./gradlew", "bootRun", "-Dspring.profiles.active=prod")
		}
	}
	finalizedBy("stopPostgresContainer")
}

