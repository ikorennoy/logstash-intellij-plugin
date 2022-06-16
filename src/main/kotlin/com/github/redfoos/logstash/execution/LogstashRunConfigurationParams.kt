package com.github.redfoos.logstash.execution

import com.intellij.execution.CommonProgramRunConfigurationParameters

interface LogstashRunConfigurationParams : CommonProgramRunConfigurationParameters {
    fun getStarterScriptPath(): String?
    fun setStarterScriptPath(path: String?)

    fun getConfigurationPath(): String?
    fun setConfigurationPath(path: String?)
}
