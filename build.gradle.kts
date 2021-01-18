group = "com.sber"
version = "1.0-SNAPSHOT"

object Versions {
    const val springBoot = "2.3.0.RELEASE"
    const val lombok = "1.18.10"
}

plugins {
    java
    id("org.springframework.boot") version "2.3.3.RELEASE"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web", version = Versions.springBoot)
    compileOnly(group = "org.projectlombok", name = "lombok", version = Versions.lombok)
    annotationProcessor(group = "org.projectlombok", name = "lombok", version = Versions.lombok)
}