package com.github.redfoos.logstash.completion

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Ignore

@Ignore
class CompletionTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData/completion"
    }
    fun testI() {
        doTest("input")
    }
    fun testE() {
        doTest("filter")
    }

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
