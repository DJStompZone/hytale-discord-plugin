plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.stompzone"
version = "1.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
    implementation("net.sf.py4j:py4j:0.10.9.9")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.build {
    dependsOn(tasks.clean)
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveClassifier.set("")
    dependsOn(tasks.jar)
}