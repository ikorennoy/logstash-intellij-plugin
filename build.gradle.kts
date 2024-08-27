import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    java
    kotlin("jvm") version "2.0.20"
    id("org.jetbrains.intellij.platform") version "2.0.1"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = properties("com.github.redfoos")

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

sourceSets["main"].java.srcDirs("src/main/gen")
sourceSets["test"].resources.srcDir("testData")


intellijPlatform {
    pluginConfiguration {
        name = properties("pluginName")
        version = properties("version")
        description = file("parts/pluginDescription.html").readText()
        changeNotes = file("parts/pluginChanges.html").readText()
    }

    publishing {
        token = System.getenv("IJ_REPO_TOKEN")
    }
}

idea {
    module {
        generatedSourceDirs.add(file("src/main/gen"))
    }
}


dependencies {
    testImplementation("org.opentest4j:opentest4j:1.3.0")
    testImplementation("junit:junit:4.13.2")
    intellijPlatform {
        intellijIdeaCommunity(properties("platformVersion"))
        instrumentationTools()
        pluginVerifier()
        testFramework(TestFrameworkType.Platform)
    }
}

tasks {
    generateParser {
        sourceFile.set(file("grammar/Logstash.bnf"))
        targetRootOutputDir.set(File("src/main/gen"))
        pathToParser.set("com/github/redfoos/logstash/LogstashParser.java")
        pathToPsiRoot.set("com/github/redfoos/logstash/psi")
    }

    generateLexer {
        dependsOn.add(generateParser)
        sourceFile.set(file("grammar/_LogstashLexer.flex"))
        targetOutputDir.set(File("src/main/gen/com/github/redfoos/logstash"))
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
