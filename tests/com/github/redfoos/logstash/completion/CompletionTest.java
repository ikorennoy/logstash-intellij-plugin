package com.github.redfoos.logstash.completion;

import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

public class CompletionTest extends LightPlatformCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "testData/completion";
    }

    public void testI() {
        doTest("input", "if", "filter");
    }

    public void testE() {
        doTest("else", "filter");
    }

    private void doTest(String... completionVariants) {
        myFixture.testCompletionVariants(getTestFilePath(), completionVariants);
    }

    private String getTestFilePath() {
        return getTestDataPath() + "/" + getTestName(true) + ".conf";
    }
}
