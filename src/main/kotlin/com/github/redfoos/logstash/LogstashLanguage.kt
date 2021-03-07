package com.github.redfoos.logstash

import com.intellij.lang.Language

class LogstashLanguage private constructor() : Language("Logstash") {

    companion object {
        @JvmStatic
        val INSTANCE = LogstashLanguage()
    }

    override fun isCaseSensitive(): Boolean {
        return true
    }
}
