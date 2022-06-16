package com.github.redfoos.logstash.execution

import com.intellij.execution.configurations.RuntimeConfigurationError
import com.intellij.ide.util.PropertiesComponent
import com.intellij.testFramework.UsefulTestCase
import junit.framework.TestCase

class RunConfigurationTest : RunConfigurationTestCase() {

    private val simpleConfig = """
        input { stdin { } }
        output { stdout { codec => rubydebug } }
    """.trimIndent()

    private val starterPath = "/usr/local/bin/logstash"

    fun testRunConfigurationFromFile() {
        val file = myFixture.configureByText("simple.conf", simpleConfig)
        val configuration = createConfiguration(file)

        TestCase.assertNotNull(configuration)
        TestCase.assertNull(configuration?.getStarterPath())
        TestCase.assertEquals(file.virtualFile.canonicalPath, configuration?.getFilePath())
    }

    fun testSetStarterPath() {
        val file = myFixture.configureByText("simple.conf", simpleConfig)
        val configuration = createConfiguration(file)

        TestCase.assertNotNull(configuration)
        TestCase.assertNull(configuration!!.getStarterPath())

        configuration.setStarterPath(starterPath)
        assertStarterPath(createConfiguration(file), starterPath)

        PropertiesComponent.getInstance().setValue(LogstashRunConfiguration.LOGSTASH_STARTER, null)
    }

    fun testStarterException() {
        val file = myFixture.configureByText("simple.conf", simpleConfig)
        val configuration = createConfiguration(file)

        UsefulTestCase.assertThrows(
            RuntimeConfigurationError::class.java
        ) { configuration!!.checkConfiguration() }

        configuration?.setStarterPath(starterPath)
    }
}
