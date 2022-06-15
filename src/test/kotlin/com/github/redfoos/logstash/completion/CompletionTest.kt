package com.github.redfoos.logstash.completion

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class CompletionTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData/completion"
    }
    fun testI() {
        doTest("input", "if", "filter")
    }
    fun testE() {
        doTest("else", "filter")
    }
    private fun doTest(vararg completionVariants: String) {
        myFixture.testCompletionVariants(getTestFilePath(), *completionVariants)
    }
    private fun getTestFilePath(): String {
        return "${getTestName(true)}.conf"
    }
}
