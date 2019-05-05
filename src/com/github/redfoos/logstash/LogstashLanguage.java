package com.github.redfoos.logstash;

import com.intellij.lang.Language;

public class LogstashLanguage extends Language {
    public static final LogstashLanguage INSTANCE = new LogstashLanguage();

    public LogstashLanguage() {
        super("Logstash");
    }
}
