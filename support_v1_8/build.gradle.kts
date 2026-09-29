// Module v1_8
plugins {
    id("java")
}

group = "com.github.fragenna2.multiversion"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

repositories {
    mavenCentral()
    maven("https://repo.codemc.io/repository/nms/")
}

dependencies {
    implementation(project(":core"))
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
}