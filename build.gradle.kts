plugins {
    `kotlin-dsl`

    id("org.eazyportal.plugin.release")
}

apply(from = "src/main/kotlin/org.eazyportal.plugin.dependency-version-lock-convention.gradle.kts")
apply(from = "src/main/kotlin/org.eazyportal.plugin.publish-convention.gradle.kts")

repositories {
    gradlePluginPortal()

    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_21.toString()))
    }
}

tasks {
    jar {
        manifest {
            attributes["Implementation-Version"] = project.version
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin", "kotlin-gradle-plugin")

    implementation("org.asciidoctor.jvm.convert", "org.asciidoctor.jvm.convert.gradle.plugin", project.properties["asciidoctorPluginVersion"] as String)
    implementation("org.asciidoctor.jvm.gems", "org.asciidoctor.jvm.gems.gradle.plugin", project.properties["asciidoctorPluginVersion"] as String) {
        // TODO: https://eazyportal.myjetbrains.com/youtrack/issue/TOOLS-54
        exclude("org.codehaus.groovy", "groovy-nio")
        // TODO: https://eazyportal.myjetbrains.com/youtrack/issue/TOOLS-54
        exclude("com.burgstaller", "okhttp-digest")
    }
    // TODO: https://eazyportal.myjetbrains.com/youtrack/issue/TOOLS-54
    implementation("io.github.rburgst", "okhttp-digest", "1.21")
    // TODO: https://eazyportal.myjetbrains.com/youtrack/issue/TOOLS-54
    implementation("org.ysb33r.gradle.jruby", "jrubygradle-resolver", "1.0.2") {
        exclude("org.codehaus.groovy", "groovy-nio")
    }
}
