package com.github.redfoos.logstash.lexer

import com.github.redfoos.logstash.LogstashLexerAdapter
import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase
import org.junit.Test

class LogstashLexerTest : LexerTestCase() {
    override fun createLexer(): Lexer {
        return LogstashLexerAdapter()
    }

    @Test
    fun testRegexp() {
        doTest("/^someRegExp$/", "Logstash token type.REGEXP ('/^someRegExp$/')")
    }

    @Test
    fun testString() {
        doTest("\"double quoted string\"", "Logstash token type.DOUBLE_QUOTED_STRING ('\"double quoted string\"')")
        doTest("'single quoted string'", "Logstash token type.SINGLE_QUOTED_STRING (''single quoted string'')")
    }

    @Test
    fun testBareword() {
        doTest("bareword", "Logstash token type.BAREWORD ('bareword')")
        doTest("@bareword", "Logstash token type.BAREWORD ('@bareword')")
    }

    @Test
    fun testNumber() {
        doTest("-1235.1423", "Logstash token type.NUMBER ('-1235.1423')")
        doTest("1234", "Logstash token type.NUMBER ('1234')")
        doTest("-11234", "Logstash token type.NUMBER ('-11234')")
        doTest("12312.5", "Logstash token type.NUMBER ('12312.5')")
    }

    @Test
    fun testComment() {
        doTest("#comment", "Logstash token type.COMMENT ('#comment')")
        doTest(" #comment", "Logstash token type.COMMENT (' #comment')")
        doTest("# comment", "Logstash token type.COMMENT ('# comment')")
        doTest(" # comment", "Logstash token type.COMMENT (' # comment')")
        doTest("#comment\n", "Logstash token type.COMMENT ('#comment\\n')")
    }



    override fun getDirPath(): String {
        return ""
    }
}
