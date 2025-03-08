import com.github.gradle.node.npm.task.NpmTask

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.flywaydb.flyway") version "11.3.4"
	id("com.github.node-gradle.node") version "7.1.0"
	java
}

group = "com.fmartinier"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

buildscript {
	dependencies {
		classpath("org.postgresql:postgresql:42.7.1")
		classpath("org.flywaydb:flyway-database-postgresql:11.3.4")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

flyway {
	url = "jdbc:postgresql://localhost:5432/postgres"
	user = "postgres"
	password = "postgres"
	schemas = arrayOf("public")
	cleanDisabled = false
}

node {
	nodeProjectDir = file("frontend/killer-party-front")
}

tasks.npmInstall {
	dependsOn(tasks.npmSetup)
}

val copyFrontend by tasks.registering(Sync::class) {
	dependsOn(buildAngularApp)
	from(file("frontend/killer-party-front/dist/killer-party-front/browser"))
	into(file("src/main/resources/static"))
}

val buildAngularApp by tasks.registering(NpmTask::class) {
	dependsOn(tasks.npmInstall)
	args = listOf("run", "build")
}

tasks.build {
	dependsOn(copyFrontend)
}

tasks.processResources {
	dependsOn(copyFrontend) // Assure que 'copyFrontend' est exécuté avant 'processResources'
}

tasks.withType<Test> {
	useJUnitPlatform()
}
