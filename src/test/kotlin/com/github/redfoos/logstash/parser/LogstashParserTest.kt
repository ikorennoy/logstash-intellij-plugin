package com.github.redfoos.logstash.parser

import com.github.redfoos.logstash.LogstashParserDefinition
import com.intellij.testFramework.ParsingTestCase

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

    fun testEmptyPluginBlock() {
        doTest(true)
    }

    fun testPluginBlock() {
        doTest(true)
    }

    fun testConditions() {
        doTest(true)
    }

    fun testDataTypes() {
        doTest(true)
    }

    fun testComplexConfig() {
        doTest(true)
    }

    fun testFieldAccess() {
        doTest(true)
    }
}
