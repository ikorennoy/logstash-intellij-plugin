package com.github.redfoos.logstash.execution

import com.intellij.execution.Location
import com.intellij.execution.PsiLocation
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.psi.PsiElement
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.MapDataContext
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import junit.framework.TestCase

open class RunConfigurationTestCase : BasePlatformTestCase() {

    protected fun assertStarterPath(configuration: LogstashRunConfiguration?, starterPath: String) {
        TestCase.assertNotNull(configuration)
        TestCase.assertEquals(starterPath, configuration!!.getStarterPath())
    }

    protected fun createConfiguration(psiElement: PsiElement): LogstashRunConfiguration? {
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
