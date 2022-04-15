/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("pinpoint.toolchain.java7")
}

dependencies {
    api(project(":pinpoint-plugin-it-utils"))
    testImplementation("org.codehaus.jackson:jackson-core-asl")
    testImplementation("org.codehaus.jackson:jackson-mapper-asl")
    testImplementation("com.fasterxml.jackson.core:jackson-core")
    testImplementation("com.fasterxml.jackson.core:jackson-annotations")
    testImplementation("com.fasterxml.jackson.core:jackson-databind")
}

description = "pinpoint-jdk-http-plugin-it"
