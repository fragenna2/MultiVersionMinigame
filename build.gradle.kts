plugins {
    id("java")
}

allprojects {
    group = "com.github.fragenna2.multiversion"
    version = "1.0.0"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
    apply(plugin = "java")

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}