package com.github.redfoos.logstash.execution

import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.configurations.RunnerSettings
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.AsyncProgramRunner
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.RunContentBuilder
import com.intellij.execution.ui.RunContentDescriptor
import org.jetbrains.concurrency.Promise
import org.jetbrains.concurrency.resolvedPromise

class LogstashProgramRunner : AsyncProgramRunner<RunnerSettings>() {

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        return DefaultRunExecutor.EXECUTOR_ID == executorId && profile is LogstashRunConfiguration
    }

    override fun execute(environment: ExecutionEnvironment, state: RunProfileState): Promise<RunContentDescriptor?> {
        return resolvedPromise(
            RunContentBuilder(
                state.execute(environment.executor, environment.runner)!!,
                environment
            ).showRunContent(environment.contentToReuse)
        )
    }

    override fun getRunnerId(): String = LOGSTASH_PROGRAM_RUNNER_ID

    companion object {
        const val LOGSTASH_PROGRAM_RUNNER_ID = "LogstashProgramRunner"
    }
}
