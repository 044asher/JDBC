tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testImplementation("org.mockito:mockito-core:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("org.mockito:mockito-junit-jupiter:3.12.4")
    implementation("org.apache.logging.log4j:log4j-core:2.22.1")
    implementation("org.apache.logging.log4j:log4j-api:2.22.1")
    implementation("org.hibernate.orm:hibernate-core:6.4.3.Final")
    implementation("mysql:mysql-connector-java:8.0.33")

}

tasks.test {
    useJUnitPlatform()
}
