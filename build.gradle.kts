plugins {
    id("java")
}

group = "org.itmo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("org.apache.hadoop:hadoop-common:3.4.1")
    implementation("org.apache.hadoop:hadoop-hdfs:3.4.1")
    implementation("org.apache.hadoop:hadoop-hdfs-client:3.4.1")
    implementation("org.apache.hadoop:hadoop-mapreduce-client-core:3.4.1")
    implementation("com.opencsv:opencsv:5.9")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")


    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}