package com.github.redfoos.logstash.highlighting;

import com.intellij.codeInsight.highlighting.BraceMatcher;
import com.intellij.codeInsight.highlighting.BraceMatchingUtil;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ParenthHighlightingTest extends LightPlatformCodeInsightFixtureTestCase {
    private final static String PAIR_MARKER = "<pair>";

    @Override
    protected String getTestDataPath() {
        return "testData/highlighting";
    }

    public void testParenth() {
        doTest();
    }

    public void testBraces() {
        doTest();
    }

    public void testBrackets() {
        doTest();
    }

    private void doTest() {
        File file = new File(getTestDataPath(), getTestName(true) + ".conf");


        String content = "";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myFixture.configureByText(file.getName(), content);

        int pairOffset = myFixture.getFile().getText().indexOf(PAIR_MARKER);

        Editor editor = myFixture.getEditor();
        Document document = editor.getDocument();

        WriteCommandAction.runWriteCommandAction(null, (Computable<Object>) () -> {
            document.replaceString(pairOffset, pairOffset + PAIR_MARKER.length(), "");
            return null;
        });

        PsiDocumentManager.getInstance(getProject()).commitDocument(document);

        int actual = BraceMatchingUtil.getMatchedBraceOffset(
                editor,
                editor.getCaretModel().getOffset() < pairOffset,
                myFixture.getFile()
        );

        TestCase.assertEquals(pairOffset, actual);

    }
}
