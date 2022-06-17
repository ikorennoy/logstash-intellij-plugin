package com.github.redfoos.logstash

import com.intellij.codeInsight.highlighting.PairedBraceMatcherAdapter

class LogstashPairedBraceMatcherAdapter : PairedBraceMatcherAdapter(LogstashBraceMatcher(), LogstashLanguage.INSTANCE)
