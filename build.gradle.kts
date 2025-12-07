plugins {
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
    val kotlinVersion = "2.2.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
}

group = "rqueue-priority-load-test"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    // rqueue lib for load test
//    implementation("com.github.sonus21:rqueue-spring-boot-starter:3.4.0-RELEASE")
//    implementation(files("libs/master/rqueue-spring-boot-starter-3.4.0-RELEASE.jar"))
//    implementation(files("libs/master/rqueue-core-3.4.0-RELEASE.jar"))
    implementation(files("libs/fix/rqueue-spring-boot-starter-3.4.0-RELEASE.jar"))
    implementation(files("libs/fix/rqueue-core-3.4.0-RELEASE.jar"))
// add transitive dependencies
    val springBootVersion = "3.0.1"
    val springVersion = "6.0.3"
    val springDataVersion = "3.0.0"
    val microMeterVersion = "1.10.2"
    val lombokVersion = "1.18.30"
    val logbackVersion = "1.4.5"
    val sl4jVersion = "2.0.6"
    val lang3Version = "3.9"
//    val jacksonVersion = "2.14.1"
    val jakartaServletVersion = "6.0.0"
    val pebbleVersion = "3.2.2"
    val lettuceVersion = "6.2.2.RELEASE"
    val jakartaAnnotationVersion = "2.1.0"
    val jakartaPersistenceVersion = "3.1.0"
    val hibernateCoreVersion = "5.6.14.Final"
    val jakartaValidationApiVersion = "3.0.2"
    val serucoEncodingVersion = "0.1.3"
    val apacheCommonCollectionVerion = "4.4"
    val hibernateValidatorVersion = "7.0.5.Final"
    val springDepManagementVersion = "1.1.0"
    val projectReactorReactorTestVersion = "3.5.1"
    val aspectjVersion = "1.9.19"
    val guavaVersion = "32.1.1-jre"

    api("org.springframework:spring-messaging:${springVersion}")
    api("org.springframework.data:spring-data-redis:${springDataVersion}")
    implementation("org.apache.commons:commons-lang3:${lang3Version}")
    implementation("com.google.guava:guava:${guavaVersion}")
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
//    api("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
//    api("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}")
//    api("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    api("jakarta.servlet:jakarta.servlet-api:${jakartaServletVersion}")
    api("jakarta.validation:jakarta.validation-api:${jakartaValidationApiVersion}")
    api("org.springframework:spring-webmvc:${springVersion}")
    api("org.springframework:spring-webflux:${springVersion}")
    api("io.pebbletemplates:pebble-spring6:${pebbleVersion}")
    api("io.seruco.encoding:base62:${serucoEncodingVersion}")
    api("org.apache.commons:commons-collections4:${apacheCommonCollectionVerion}")
    api("org.hibernate.validator:hibernate-validator:${hibernateValidatorVersion}")
    api("io.micrometer:micrometer-core:${microMeterVersion}")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.13")
}

