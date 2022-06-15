package com.github.redfoos.logstash.execution

import com.github.redfoos.logstash.Icons
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.SimpleConfigurationType
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NotNullLazyValue

class LogstashConfigurationType : SimpleConfigurationType(
    "LogstashApplication", "Logstash", null, NotNullLazyValue.createConstantValue(
        Icons.logstash
    )
) {
    override fun createConfiguration(name: String?, template: RunConfiguration): RunConfiguration {
        val runConfiguration = super.createConfiguration(name, template)
        if (runConfiguration is LogstashRunConfiguration) {
            var path = PropertiesComponent.getInstance(runConfiguration.project)
                .getValue(LogstashRunConfiguration.LOGSTASH_STARTER)
            if (path == null) {
                path = PropertiesComponent.getInstance().getValue(LogstashRunConfiguration.LOGSTASH_STARTER)
            }
            if (path != null) {
                runConfiguration.setStarterPath(path)
            }
        }
        return runConfiguration
    }

    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return LogstashRunConfiguration(project, this)
    }
}
