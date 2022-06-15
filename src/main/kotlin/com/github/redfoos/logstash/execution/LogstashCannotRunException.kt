package com.github.redfoos.logstash.execution

import com.intellij.execution.ExecutionException

class LogstashCannotRunException(message: String) : ExecutionException(message) {
    companion object {
        fun logstashStarterNotFound(): LogstashCannotRunException {
            return LogstashCannotRunException("Logstash starter is not found.")
        }

        fun fileNotSetUp(): LogstashCannotRunException {
            return LogstashCannotRunException("File is not setup.")
        }
    }
}
