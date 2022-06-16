package com.github.redfoos.logstash.execution

import com.github.redfoos.logstash.psi.LogstashFile
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

class LogstashRunConfigurationProducer : LazyRunConfigurationProducer<LogstashRunConfiguration>() {

    override fun isConfigurationFromContext(
        configuration: LogstashRunConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val logstashFile = context.location?.psiElement
        if (logstashFile !is LogstashFile) return false
        return configuration.getFilePath() == logstashFile.virtualFile.canonicalPath
    }

    override fun setupConfigurationFromContext(
        configuration: LogstashRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val logstashFile = sourceElement.get()
        if (logstashFile !is LogstashFile) return false
        val path = logstashFile.virtualFile.canonicalPath ?: return false
        configuration.setFilePath(path)
        configuration.name = logstashFile.virtualFile.name
        return true
    }

    override fun getConfigurationFactory(): ConfigurationFactory {
        return LogstashConfigurationFactory(LogstashConfigurationType.instance)
    }
}
