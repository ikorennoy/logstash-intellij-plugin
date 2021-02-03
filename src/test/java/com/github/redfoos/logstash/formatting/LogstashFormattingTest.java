package com.github.redfoos.logstash.formatting;

import com.github.redfoos.logstash.LogstashLanguage;
import com.intellij.application.options.CodeStyle;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class LogstashFormattingTest extends BasePlatformTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/formatting";
    }

    public void testEmptyPluginBlock_default() {
        doWholeFileTest();
    }

    public void testComplexConfig_default() {
        doWholeFileTest();
    }

    private void doWholeFileTest() {
        doWholeFileTest(() -> {
        });
    }

    private void doWholeFileTest(Runnable configureOptions) {
        doCommonTest(configureOptions, codeStyleManager -> codeStyleManager.reformat(myFixture.getFile()));
    }

    private void doPartialReformatTest(int startLine, int endLine) {
        doCommonTest(() -> {
        }, codeStyleManager -> {
            Editor editor = myFixture.getEditor();
            int start = editor.logicalPositionToOffset(new LogicalPosition(startLine, 0));
            int end = editor.logicalPositionToOffset(new LogicalPosition(endLine, 0));
            codeStyleManager.reformatRange(myFixture.getFile(), start, end);
        });
    }

    private void doCommonTest(@NotNull Runnable configureOptions, @NotNull Consumer<CodeStyleManager> reformat) {
        String testName = getTestName(true);
        int split = testName.indexOf('_');
        String source = split != -1 ? testName.substring(0, split) : testName;
        String resultName = testName.replace('_', '.');
        myFixture.configureByFile(source + ".conf");
        configureOptions.run();
        WriteCommandAction.runWriteCommandAction(null, () -> {
            CodeStyleManager codeStyleManager = CodeStyleManager.getInstance(myFixture.getProject());
            reformat.accept(codeStyleManager);
        });
        myFixture.checkResultByFile(resultName + ".txt");
    }

    @NotNull
    private CommonCodeStyleSettings getCommonSettings() {
        return CodeStyle.getLanguageSettings(myFixture.getFile(), LogstashLanguage.INSTANCE);
    }
}
