package com.github.redfoos.logstash.psi

import com.github.redfoos.logstash.LogstashLanguage
import com.intellij.psi.tree.IElementType

class LogstashTokenType(debugName: String) : IElementType(debugName, LogstashLanguage.INSTANCE) {

    override fun toString(): String {
        return "Logstash token type.${super.toString()}"
    }
}
