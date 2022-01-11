plugins {
    id("eazy-kotlin-conventions")

    id("java-library")
    id("maven-publish")
}

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(project.components["kotlin"])
        }
    }
}

tasks {
    jar {
        manifest {
            attributes["Implementation-Version"] = project.version
        }
    }
}
