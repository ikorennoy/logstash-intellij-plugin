package com.github.redfoos.logstash.execution

import com.intellij.execution.Location
import com.intellij.execution.PsiLocation
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.configurations.RuntimeConfigurationError
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.psi.PsiElement
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.MapDataContext
import com.intellij.testFramework.UsefulTestCase
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import junit.framework.TestCase
import org.junit.Test


class RunConfigurationTest : BasePlatformTestCase() {

    private val simpleConfig = """
        input { stdin { } }
        output { stdout { codec => rubydebug } }
    """.trimIndent()

    private val starterPath = "/usr/local/bin/logstash"


    @Test
    fun testRunConfigurationFromFile() {
        val file = myFixture.configureByText("simple.conf", simpleConfig)
        val configuration = createConfiguration(file)

        TestCase.assertNotNull(configuration)
        TestCase.assertNull(configuration?.getStarterScriptPath())
        TestCase.assertEquals(file.virtualFile.canonicalPath, configuration?.getConfigurationPath())
    }

    @Test
    fun testSetStarterPath() {
        val file = myFixture.configureByText("simple.conf", simpleConfig)
        val configuration = createConfiguration(file)

        TestCase.assertNotNull(configuration)
        TestCase.assertNull(configuration!!.getStarterScriptPath())

        configuration.setStarterScriptPath(starterPath)
        assertStarterPath(createConfiguration(file), starterPath)

        PropertiesComponent.getInstance().setValue(LogstashRunConfiguration.LOGSTASH_STARTER, null)
    }

    @Test
    fun testStarterNotFoundException() {
        val file = myFixture.configureByText("simple.conf", simpleConfig)
        val configuration = createConfiguration(file)!!

        UsefulTestCase.assertThrows(
            RuntimeConfigurationError::class.java
        ) { configuration.checkConfiguration() }

        configuration.setStarterScriptPath(starterPath)
        configuration.checkConfiguration()
    }

    @Test
    fun testConfigurationNotFound() {
        val file = myFixture.configureByText("simple.conf", simpleConfig)
        val configuration = createConfiguration(file)
        configuration!!.setStarterScriptPath("/starter/path")
        configuration.setConfigurationPath("")
        UsefulTestCase.assertThrows(
            RuntimeConfigurationError::class.java
        ) { configuration.checkConfiguration() }

        configuration.setConfigurationPath(file.virtualFile.canonicalPath)
        configuration.checkConfiguration()
    }

    private fun assertStarterPath(configuration: LogstashRunConfiguration?, starterPath: String) {
        TestCase.assertNotNull(configuration)
        TestCase.assertEquals(starterPath, configuration!!.getStarterScriptPath())
    }

    private fun createConfiguration(psiElement: PsiElement): LogstashRunConfiguration? {
        return createConfiguration(psiElement, MapDataContext())
    }

    private fun createConfiguration(psiElement: PsiElement, dataContext: MapDataContext): LogstashRunConfiguration? {
        val context = createContext(psiElement, dataContext)
        val settings = context.configuration
        settings ?: return null
        val config = settings.configuration
        if (config is LogstashRunConfiguration) {
            return config
        }
        return null
    }

    private fun createContext(psiClass: PsiElement, dataContext: MapDataContext): ConfigurationContext {
        dataContext.put(CommonDataKeys.PROJECT, project)
        if (LangDataKeys.MODULE.getData(dataContext) == null) {
            dataContext.put(LangDataKeys.MODULE, ModuleUtilCore.findModuleForPsiElement(psiClass))
        }
        dataContext.put(Location.DATA_KEY, PsiLocation.fromPsiElement(psiClass))
        return ConfigurationContext.getFromContext(dataContext, "")
    }

    override fun getProjectDescriptor(): LightProjectDescriptor {
        return LightProjectDescriptor()
    }
}
