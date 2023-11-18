buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.2.0")
    }
}
repositories {
    mavenCentral()
}

plugins {
    id("java")
    id("eclipse")
    id("idea")
}

group = "org.jeremiahboothe"
version = "1.0-SNAPSHOT"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.test {
    useJUnitPlatform()
}