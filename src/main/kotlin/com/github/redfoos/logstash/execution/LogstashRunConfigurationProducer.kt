package com.github.redfoos.logstash.execution

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

class LogstashRunConfigurationProducer : LazyRunConfigurationProducer<LogstashRunConfiguration>() {

    override fun isConfigurationFromContext(
        configuration: LogstashRunConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val configuredFile = configuration.getConfigurationPath()
        val psiFile = context.dataContext.getData(PlatformDataKeys.PSI_FILE) ?: return false
        val currentFile = psiFile.virtualFile ?: return false
        return configuredFile == currentFile.canonicalPath
    }

    override fun setupConfigurationFromContext(
        configuration: LogstashRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val location = context.location ?: return false
        val container = location.psiElement.containingFile ?: return false
        val logstashFile = container.virtualFile ?: return false

        val path = logstashFile.canonicalPath ?: return false
        configuration.setConfigurationPath(path)
        configuration.name = logstashFile.name
        val logstashStarterPath: String? =
            PropertiesComponent.getInstance(context.project).getValue(LogstashRunConfiguration.LOGSTASH_STARTER)
        configuration.setStarterScriptPath(logstashStarterPath)
        return true
    }

    override fun getConfigurationFactory(): ConfigurationFactory {
        return LogstashConfigurationFactory(LogstashConfigurationType.instance)
    }
}
