plugins {
    id("java")
    id("idea")
    id("eclipse")
    id("maven-publish")
    id("org.springframework.boot") version "2.1.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_10
    targetCompatibility = JavaVersion.VERSION_1_10
}

group = "uk.co.mulecode.tpm"
version = "1.0.0-SNAPSHOT"

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-parent:2.1.3.RELEASE")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Greenwich.RELEASE")
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.kafka:spring-kafka:2.2.6.RELEASE")

    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.7")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.7")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.7")

    implementation("com.github.javafaker:javafaker:0.14")

    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("org.apache.commons:commons-lang3:3.8.1")
    implementation("org.apache.commons:commons-collections4:4.0")

    testImplementation("junit:junit:4.12")
    testImplementation("com.github.javafaker:javafaker:0.14")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("com.jayway.jsonpath:json-path-assert:2.4.0")
}