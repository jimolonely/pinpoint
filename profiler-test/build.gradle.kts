/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("pinpoint.java-conventions")
}

dependencies {
    api(project(":pinpoint-commons"))
    api(project(":pinpoint-profiler"))
    api(project(":pinpoint-thrift"))
    api(project(":pinpoint-bootstrap-core"))
    api(project(":pinpoint-bootstrap"))
    api(project(":pinpoint-rpc"))
    implementation("org.ow2.asm:asm:9.2")
    implementation("org.ow2.asm:asm-commons:9.2")
    implementation("org.ow2.asm:asm-util:9.2")
    implementation("org.ow2.asm:asm-tree:9.2")
    implementation("org.apache.thrift:libthrift:0.12.0")
    runtimeOnly("org.slf4j:slf4j-api:1.7.30")
    runtimeOnly("org.apache.logging.log4j:log4j-jcl:2.12.4")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.12.4")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.12.4")
    testImplementation("org.apache.commons:commons-lang3:3.8.1")
}

description = "pinpoint-profiler-test"
