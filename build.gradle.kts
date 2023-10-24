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
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.microsoft.playwright:playwright:1.38.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.microsoft.playwright.CLI")
}
