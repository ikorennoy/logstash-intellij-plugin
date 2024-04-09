import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    java
    kotlin("jvm") version "1.9.23"
    id("org.jetbrains.intellij") version "1.17.3"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = properties("com.github.redfoos")

repositories {
    mavenCentral()
}

sourceSets["main"].java.srcDirs("src/main/gen")
sourceSets["test"].resources.srcDir("testData")


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
        sourceFile.set(file("grammar/Logstash.bnf"))
        targetRootOutputDir.set(File("src/main/gen"))
        pathToParser.set("com/github/redfoos/logstash/LogstashParser.java")
        pathToPsiRoot.set("com/github/redfoos/logstash/psi")
    }

    generateLexer {
        dependsOn.add(generateParser)
        sourceFile.set(file("grammar/_LogstashLexer.flex"))
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
