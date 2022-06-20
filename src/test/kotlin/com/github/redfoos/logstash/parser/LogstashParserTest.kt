package com.github.redfoos.logstash.parser

import com.github.redfoos.logstash.LogstashParserDefinition
import com.intellij.testFramework.ParsingTestCase
import org.junit.Test

class LogstashParserTest : ParsingTestCase("", "conf", LogstashParserDefinition()) {
    override fun getTestDataPath(): String {
        return "src/test/testData/parser"
    }

    override fun skipSpaces(): Boolean {
        return false
    }

    override fun includeRanges(): Boolean {
        return true
    }

    @Test
    fun testEmptyPluginBlock() {
        doTest(true)
    }

    @Test
    fun testPluginBlock() {
        doTest(true)
    }

    @Test
    fun testConditions() {
        doTest(true)
    }

    @Test
    fun testDataTypes() {
        doTest(true)
    }

    @Test
    fun testComplexConfig() {
        doTest(true)
    }

    @Test
    fun testFieldAccess() {
        doTest(true)
    }
}
