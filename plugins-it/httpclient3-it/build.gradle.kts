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
    testImplementation("commons-httpclient:commons-httpclient:3.1")
}

description = "pinpoint-httpclient3-plugin-it"
