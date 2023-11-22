plugins {
    application
    java
}

group = "com.jakobzeise"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    implementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    implementation("org.junit.jupiter:junit-jupiter-params:5.6.2")

    implementation("com.microsoft.playwright:playwright:1.38.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.microsoft.playwright.CLI")
}
