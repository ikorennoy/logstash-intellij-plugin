package com.github.redfoos.logstash.highlighting;

import com.github.redfoos.logstash.LogstashLanguage;
import com.intellij.codeInsight.highlighting.PairedBraceMatcherAdapter;

public class LogstashPairedBraceMatcher extends PairedBraceMatcherAdapter {
    public LogstashPairedBraceMatcher() {
        super(new LogstashBraceMatcher(), LogstashLanguage.getINSTANCE());
    }
}
