package com.github.redfoos.logstash;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class LogstashLexerAdapter extends FlexAdapter {
    public LogstashLexerAdapter() {
        super(new LogstashLexer((Reader) null));
    }
}
