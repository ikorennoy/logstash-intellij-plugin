package com.github.redfoos.logstash.hightlighting

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import java.io.File
import java.io.IOException
import java.nio.file.Files

class HighlightingTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData/highlighting"
    }

    fun testPluginBlockDefinition() {
        doTest()
    }

    fun testFunctionalElement() {
        doTest()
    }

    private fun doTest() {
        val file = File(testDataPath, getTestName(true) + ".conf")
        var content = ""
        try {
            content = String(Files.readAllBytes(file.toPath()))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        myFixture.configureByText(file.name, content)
        myFixture.testHighlighting()
    }
}
