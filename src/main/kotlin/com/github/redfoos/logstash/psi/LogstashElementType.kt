package com.github.redfoos.logstash.psi

import com.github.redfoos.logstash.LogstashLanguage
import com.intellij.psi.tree.IElementType

class LogstashElementType(debugName: String): IElementType(debugName, LogstashLanguage.INSTANCE)
