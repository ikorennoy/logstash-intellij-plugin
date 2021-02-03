package com.github.redfoos.logstash.highlighting;

import com.intellij.testFramework.fixtures.BasePlatformTestCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HighlightingTest extends BasePlatformTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/highlighting";
    }

    public void testPluginBlockDefinition() {
        doTest();
    }

    public void testFunctionalElement() {
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
        myFixture.testHighlighting();

    }
}
