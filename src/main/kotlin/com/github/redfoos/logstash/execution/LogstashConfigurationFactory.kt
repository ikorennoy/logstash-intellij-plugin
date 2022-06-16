package com.github.redfoos.logstash.execution

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.project.Project

class LogstashConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type) {
    companion object {
        private const val factoryName = "Logstash configuration factory"
    }

    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return LogstashRunConfiguration(project, this)
    }

    override fun getName(): String = factoryName

    override fun getId(): String = factoryName
}
