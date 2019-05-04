package com.github.redfoos.logstash;

import com.intellij.codeInsight.highlighting.PairedBraceMatcherAdapter;

public class LogstashPairedBraceMatcher extends PairedBraceMatcherAdapter {
    public LogstashPairedBraceMatcher() {
        super(new LogstashBraceMatcher(), LogstashLanguage.INSTANCE);
    }
}
