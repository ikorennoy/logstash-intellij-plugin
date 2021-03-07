package com.github.redfoos.logstash.lexer;

import com.github.redfoos.logstash.LogstashLexerAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.testFramework.LexerTestCase;
import org.junit.Ignore;

@Ignore
public class LogstashLexerTest extends LexerTestCase {
    @Override
    protected Lexer createLexer() {
        return new LogstashLexerAdapter();
    }


    public void testRegexp() {
        doTest("/^someRegExp$/", "Logstash token type.REGEXP ('/^someRegExp$/')");
    }

    public void testString() {
        doTest("\"double quoted string\"", "Logstash token type.STRING ('\"double quoted string\"')");
        doTest("'single quoted string'", "Logstash token type.STRING (''single quoted string'')");
    }

    @Override
    protected String getDirPath() {
        return "";
    }
}
