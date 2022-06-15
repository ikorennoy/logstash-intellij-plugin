package com.github.redfoos.logstash.execution

import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.KillableProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.util.ProgramParametersUtil

class LogstashCommandLineState(
    private val runConfiguration: LogstashRunConfiguration,
    environment: ExecutionEnvironment
) : CommandLineState(environment) {

    override fun startProcess(): ProcessHandler {
        val workingDir = findWorkingDir(runConfiguration)
        val cmd = createCommandLine(workingDir, runConfiguration)

        val processHandler = KillableProcessHandler(cmd)
        ProcessTerminatedListener.attach(processHandler, environment.project)

        return processHandler
    }

    private fun createCommandLine(workingDir: String, runConfiguration: LogstashRunConfiguration): GeneralCommandLine {
        val cmd = GeneralCommandLine()
        val starter = runConfiguration.getStarterPath()!!
        cmd.exePath = starter

        if (!runConfiguration.programParameters.isNullOrBlank()) {
            cmd.addParameters(runConfiguration.programParameters!!.split(" "))
        }

        cmd.addParameters("-f", "${runConfiguration.getFilePath()}")
        cmd.withWorkDirectory(workingDir)
        cmd.withParentEnvironmentType(if (runConfiguration.isPassParentEnvs) GeneralCommandLine.ParentEnvironmentType.CONSOLE else GeneralCommandLine.ParentEnvironmentType.NONE)
        cmd.withEnvironment(runConfiguration.envs)
        return cmd
    }

    private fun findWorkingDir(runConfiguration: LogstashRunConfiguration): String {
        return ProgramParametersUtil.getWorkingDir(runConfiguration, runConfiguration.project, null)
    }
}
