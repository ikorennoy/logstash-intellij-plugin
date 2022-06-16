package com.github.redfoos.logstash.execution

import com.intellij.execution.Executor
import com.intellij.execution.configuration.EnvironmentVariablesComponent
import com.intellij.execution.configurations.*
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMExternalizerUtil
import org.jdom.Element
import java.lang.Boolean.parseBoolean

class LogstashRunConfiguration(
    project: Project,
    configurationFactory: ConfigurationFactory
) : LocatableConfigurationBase<LogstashCommandLineState>(project, configurationFactory, "Logstash run configuration"),
    LogstashRunConfigurationParams {

    private var workingDir: String? = null
    private var starterParams: String? = null
    private var starterPath: String? = null
    private var filePath: String? = null


    private val myEnvs = LinkedHashMap<String, String>()
    private var myPassParentEnvs = true

    override fun getStarterPath(): String? = starterPath
    override fun setStarterPath(path: String?) {
        if (path.isNullOrBlank()) {
            starterPath = null
        } else {
            starterPath = path
            PropertiesComponent.getInstance(project).setValue(LOGSTASH_STARTER, path)
            PropertiesComponent.getInstance().setValue(LOGSTASH_STARTER, path)
        }
    }

    override fun getFilePath(): String? = filePath
    override fun setFilePath(path: String?) {
        filePath = path
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> = LogstashSettingsEditor()

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return LogstashCommandLineState(this, environment)
    }

    override fun checkConfiguration() {
        if (starterPath == null) {
            throw RuntimeConfigurationError("Run configuration is invalid: no starter selected")
        }

        if (filePath == null) {
            throw RuntimeConfigurationError("Run configuration is invalid: no logstash configuration selected")
        }
    }

    override fun getWorkingDirectory(): String? = workingDir

    override fun setWorkingDirectory(dir: String?) {
        workingDir = dir
    }

    override fun setProgramParameters(parameters: String?) {
        starterParams = parameters
    }

    override fun getProgramParameters(): String? = starterParams

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        JDOMExternalizerUtil.writeField(element, "FILE_PATH", filePath)
        JDOMExternalizerUtil.writeField(element, "STARTER_PATH", starterPath)
        JDOMExternalizerUtil.writeField(element, "WORKING_DIRECTORY", workingDirectory)
        JDOMExternalizerUtil.writeField(element, "PARENT_ENVS", isPassParentEnvs.toString())
        JDOMExternalizerUtil.writeField(element, "PARAMETERS", starterParams)
        EnvironmentVariablesComponent.writeExternal(element, myEnvs)
    }


    override fun readExternal(element: Element) {
        super.readExternal(element)
        EnvironmentVariablesComponent.readExternal(element, myEnvs)

        filePath = JDOMExternalizerUtil.readField(element, "FILE_PATH")
        if (filePath.isNullOrBlank()) {
            filePath = null
        }
        starterPath = JDOMExternalizerUtil.readField(element, "STARTER_PATH")
        if (starterPath.isNullOrBlank()) {
            starterPath = null
        }
        workingDir = JDOMExternalizerUtil.readField(element, "WORKING_DIRECTORY")
        programParameters = JDOMExternalizerUtil.readField(element, "PARAMETERS")
        val parentEnvValue = JDOMExternalizerUtil.readField(element, "PARENT_ENVS")
        if (parentEnvValue != null) {
            isPassParentEnvs = parseBoolean(parentEnvValue)
        }

    }

    override fun getEnvs(): MutableMap<String, String> {
        return myEnvs
    }

    override fun setEnvs(envs: MutableMap<String, String>) {
        myEnvs.clear()
        myEnvs.putAll(envs)
    }

    override fun isPassParentEnvs(): Boolean = myPassParentEnvs

    override fun setPassParentEnvs(passParentEnvs: Boolean) {
        myPassParentEnvs = passParentEnvs
    }

    companion object {
        const val LOGSTASH_STARTER = "LOGSTASH_STARTER"
    }
}
