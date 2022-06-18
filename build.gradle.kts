import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    java
    kotlin("jvm") version "1.7.0"
    id("org.jetbrains.intellij") version "1.6.0"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = properties("com.github.redfoos")

repositories {
    mavenCentral()
}

sourceSets["main"].java.srcDirs("src/main/gen")
sourceSets["test"].resources.srcDir("testData")

java.sourceCompatibility = JavaVersion.VERSION_11

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



tasks {
    patchPluginXml {
        version.set(properties("version"))
        pluginDescription.set(file("parts/pluginDescription.html").readText())
        changeNotes.set(file("parts/pluginChanges.html").readText())
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

    publishPlugin {
        token.set(System.getenv("IJ_REPO_TOKEN"))
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

tasks.clean {
    doFirst {
        delete.add("src/main/gen")
    }
}
