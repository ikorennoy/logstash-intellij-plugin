package com.github.redfoos.logstash.execution

import com.github.redfoos.logstash.psi.LogstashFile
import com.intellij.execution.ExecutionManager
import com.intellij.execution.RunManager
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.RunConfigurationProducer
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.ExecutionEnvironmentBuilder
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAwareAction

class LogstashRunFileAction : DumbAwareAction() {
    companion object {
        const val ID = "runLogstashFileAction"
    }

    override fun actionPerformed(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.PSI_FILE) ?: return
        val path = file.virtualFile ?: return
        val context = ConfigurationContext.getFromContext(e.dataContext, e.place)
        val configurationProducer = RunConfigurationProducer.getInstance(LogstashRunConfigurationProducer::class.java)
        val settings = configurationProducer.findExistingConfiguration(context)
        val project = file.project
        val runConfiguration: LogstashRunConfiguration
        if (settings == null) {
            val newSettings =
                RunManager.getInstance(project).createConfiguration(file.name, LogstashConfigurationType::class.java)
            runConfiguration = newSettings.configuration as LogstashRunConfiguration
            runConfiguration.setFilePath(path.canonicalPath)
        } else {
            runConfiguration = settings.configuration as LogstashRunConfiguration
        }

        val builder = ExecutionEnvironmentBuilder.createOrNull(DefaultRunExecutor.getRunExecutorInstance(), runConfiguration)
        if (builder != null) {
            ExecutionManager.getInstance(project).restartRunProfile(builder.build())
        }
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = isEnabled(e)
    }

    private fun isEnabled(e: AnActionEvent): Boolean {
        if (e.project != null) {
            val psiFile = e.getData(CommonDataKeys.PSI_FILE)
            if (psiFile != null) {
                if (psiFile is LogstashFile) return true
            }
        }
        return false
    }
}
