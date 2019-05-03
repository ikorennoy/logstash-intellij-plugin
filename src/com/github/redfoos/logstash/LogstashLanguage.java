package com.github.redfoos.logstash;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import org.jetbrains.annotations.NotNull;

public class LogstashLanguage extends Language {
    public static final LogstashLanguage INSTANCE = new LogstashLanguage();

    public LogstashLanguage() {
        super("Logstash");
    }
}
