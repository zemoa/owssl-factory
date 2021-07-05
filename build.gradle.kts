import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
}

repositories {
    mavenCentral()
}

javafx {
    version = "16"
    modules = mutableListOf("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.openjfx:javafx-controls:16")
    implementation("org.openjfx:javafx-fxml:16")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.palexdev:materialfx:11.11.1")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
}

group = "zemoa"
version = "0.0.1-SNAPSHOT"
description = "owssl-factory"
java.sourceCompatibility = JavaVersion.VERSION_16

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

//application {
//    mainClass.set("zemoa/OwsslFactoryLauncher")
//}
