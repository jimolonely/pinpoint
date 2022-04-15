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
    testImplementation(project(":pinpoint-mongodb-driver-plugin"))
    testImplementation("org.mongodb:bson:3.7.0")
    testImplementation("org.mongodb:mongodb-driver:3.9.0")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:1.50.5")
}

description = "pinpoint-mongodb-plugin-it"
