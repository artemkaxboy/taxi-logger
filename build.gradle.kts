import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"

    /*-------------------------------- JIB -----------------------------------------------*/
    id("com.google.cloud.tools.jib") version "3.0.0"
    /*-------------------------------- JIB -----------------------------------------------*/

}

group = "com.artemkaxboy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")
extra["testcontainersVersion"] = "1.16.2"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mongodb")

    // validation
    implementation("org.hibernate:hibernate-validator:7.0.1.Final")

    //	Metrics
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    //	Logging
    implementation("io.github.microutils:kotlin-logging:2.1.21")

    //	Cache
    implementation("org.springframework.boot:spring-boot-starter-cache")

}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//tasks.test {
//    outputs.dir(snippetsDir)
//}

//tasks.asciidoctor {
//    inputs.dir(snippetsDir)
//    dependsOn(test)
//}

/*-------------------------------- JIB -----------------------------------------------*/

jib {
    val applicationDescription: String by project
    val lastCommitTime: String by project
    val lastCommitHash: String by project
    val author: String by project
    val sourceUrl: String by project
    val refName: String by project

    from {
        platforms {
            platform {
                architecture = "amd64"
                os = "linux"
            }

            platform {
                architecture = "arm64"
                os = "linux"
            }
        }
    }

    container {
        user = "999:999"
        creationTime = lastCommitTime
        ports = listOf("8080")

        environment = mapOf(
            "APPLICATION_NAME" to name,
            "APPLICATION_VERSION" to "$version",
            "APPLICATION_REVISION" to lastCommitHash
        )

        labels = mapOf(
            "maintainer" to author,
            "org.opencontainers.image.created" to lastCommitTime,
            "org.opencontainers.image.authors" to author,
            "org.opencontainers.image.url" to sourceUrl,
            "org.opencontainers.image.documentation" to sourceUrl,
            "org.opencontainers.image.source" to sourceUrl,
            "org.opencontainers.image.version" to "$version",
            "org.opencontainers.image.revision" to lastCommitHash,
            "org.opencontainers.image.vendor" to author,
            "org.opencontainers.image.ref.name" to refName,
            "org.opencontainers.image.title" to name,
            "org.opencontainers.image.description" to applicationDescription,
        )
    }
}
