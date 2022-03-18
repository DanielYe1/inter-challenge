/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.6.1")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.1")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:3.0.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.1")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.8.2")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "inter"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
