package com.github.redfoos.logstash.execution

import com.intellij.execution.CommonProgramRunConfigurationParameters

interface LogstashRunConfigurationParams : CommonProgramRunConfigurationParameters {
    fun getStarterPath(): String?
    fun setStarterPath(path: String?)

    fun getFilePath(): String?
    fun setFilePath(path: String?)
}
