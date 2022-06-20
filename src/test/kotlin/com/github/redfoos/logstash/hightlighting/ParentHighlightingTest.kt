package com.github.redfoos.logstash.hightlighting

import com.intellij.codeInsight.highlighting.BraceMatchingUtil
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.util.Computable
import com.intellij.psi.PsiDocumentManager
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test
import java.io.File
import java.io.IOException
import java.nio.file.Files

class ParenthHighlightingTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData/highlighting"
    }

    @Test
    fun testParenth() {
        doTest()
    }

    @Test
    fun testBraces() {
        doTest()
    }

    @Test
    fun testBrackets() {
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
        val pairOffset = myFixture.file.text.indexOf(PAIR_MARKER)
        val editor = myFixture.editor
        val document = editor.document
        WriteCommandAction.runWriteCommandAction(null, Computable<Any?> {
            document.replaceString(pairOffset, pairOffset + PAIR_MARKER.length, "")
            null
        })
        PsiDocumentManager.getInstance(project).commitDocument(document)
        val actual = BraceMatchingUtil.getMatchedBraceOffset(
            editor,
            editor.caretModel.offset < pairOffset,
            myFixture.file
        )
        assertEquals(pairOffset, actual)
    }

    companion object {
        private const val PAIR_MARKER = "<pair>"
    }
}
