//package com.github.redfoos.logstash.highlighting;
//
//import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//
//public class HighlightingTest extends LightPlatformCodeInsightFixtureTestCase {
//
//    @Override
//    protected String getTestDataPath() {
//        return "testData/highlighting";
//    }
//
//    public void testFunctionalCall() {
//        doTest();
//    }
//
//    public void testFunctionalDefinition() {
//        doTest();
//    }
//
//    private void doTest() {
//        File file = new File(getTestDataPath(), getTestName(true) + ".conf");
//
//        String content = "";
//        try {
//            content = new String(Files.readAllBytes(file.toPath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        myFixture.configureByText(file.getName(), content);
//        myFixture.testHighlighting();
//
//    }
//}
