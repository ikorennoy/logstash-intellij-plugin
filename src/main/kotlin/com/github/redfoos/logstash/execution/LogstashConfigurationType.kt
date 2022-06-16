package com.github.redfoos.logstash.execution

import com.github.redfoos.logstash.Icons
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.runConfigurationType
import javax.swing.Icon

class LogstashConfigurationType : ConfigurationType {
    companion object {
        val instance: LogstashConfigurationType
            get() = runConfigurationType()
    }

    override fun getDisplayName(): String = "Logstash"

    override fun getConfigurationTypeDescription(): String = "Run Logstash configuration"

    override fun getIcon(): Icon = Icons.logstash

    override fun getId(): String = "LOGSTASH_RUN_CONFIGURATION"

    override fun getConfigurationFactories(): Array<ConfigurationFactory> = arrayOf(LogstashConfigurationFactory(this))
}
