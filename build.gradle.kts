import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "1.7.0"
    id("org.jetbrains.intellij") version "1.6.0"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
}

tasks {
    wrapper {
        version = properties("gradleVersion")
    }

    intellij {
        pluginName.set(properties("pluginName"))
        version.set(properties("platformVersion"))
        type.set(properties("platformType"))
    }

    idea {
        module {
            generatedSourceDirs.add(file("src/main/gen"))
        }
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))
        pluginDescription.set(file(properties("descriptionFile")).readText())
        changeNotes.set(file(properties("changesFile")).readText())
    }

    generateParser {
        source.set("grammar/Logstash.bnf")
        targetRoot.set("src/main/gen")
        pathToParser.set("com/github/redfoos/logstash/LogstashParser.java")
        pathToPsiRoot.set("com/github/redfoos/logstash/psi")
    }

    generateLexer {
        dependsOn.add(generateParser)
        source.set("grammar/_LogstashLexer.flex")
        targetDir.set("src/main/gen/com/github/redfoos/logstash")
        targetClass.set("LogstashLexer")
    }
}

tasks.withType<JavaCompile> {
    dependsOn.add(tasks.getByName("generateLexer"))
    targetCompatibility = properties("javaVersion")
    sourceCompatibility = properties("javaVersion")
}

tasks.withType<KotlinCompile> {
    dependsOn.add(tasks.getByName("generateLexer"))
    kotlinOptions.jvmTarget = properties("javaVersion")
}

sourceSets["main"].java.srcDirs("src/main/gen")
sourceSets["test"].resources.srcDir("testData")


tasks.clean {
    doFirst {
        delete.add("src/main/gen")
    }
}
