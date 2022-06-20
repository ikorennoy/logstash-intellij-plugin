package com.github.redfoos.logstash.completion

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Ignore
import org.junit.Test

class CompletionTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData/completion"
    }
    @Test
    fun testI() {
        doTest("input", "filter")
    }
    @Test
    fun testE() {
        doTest("filter")
    }

    @Test
    @Ignore
    fun testO() {
        doTest("output")
    }
    private fun doTest(vararg completionVariants: String) {
        myFixture.testCompletionVariants(getTestFilePath(), *completionVariants)
    }
    private fun getTestFilePath(): String {
        return "${getTestName(true)}.conf"
    }
}
