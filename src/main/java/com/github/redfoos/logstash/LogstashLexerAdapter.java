package com.github.redfoos.logstash;

import com.intellij.lexer.FlexAdapter;

public class LogstashLexerAdapter extends FlexAdapter {
    public LogstashLexerAdapter() {
        super(new _LogstashLexer());
    }
}
