package com.github.redfoos.logstash.completion;

import com.github.redfoos.logstash.LogstashLanguage;
import com.github.redfoos.logstash.psi.LogstashTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class LogstashKeywordCompletionContributor extends CompletionContributor {
    private final static PsiElementPattern.Capture<PsiElement> psiElementCapture = PlatformPatterns.psiElement().withLanguage(LogstashLanguage.getINSTANCE());
    private final static PsiElementPattern.Capture<PsiElement> ifPattern = PlatformPatterns.psiElement(LogstashTypes.IF_TOK);
    private final static PsiElementPattern.Capture<PsiElement> elsePattern = PlatformPatterns.psiElement(LogstashTypes.ELSE_TOK);
    private final static PsiElementPattern.Capture<PsiElement> andPattern = PlatformPatterns.psiElement(LogstashTypes.AND);
    private final static PsiElementPattern.Capture<PsiElement> pluginBlockPattern = PlatformPatterns.psiElement(LogstashTypes.PLUGIN_BLOCK);

    public LogstashKeywordCompletionContributor() {

        ElementPattern<PsiElement> patterns = PlatformPatterns.or(psiElementCapture, ifPattern, elsePattern, andPattern, pluginBlockPattern);

        CompletionProvider<CompletionParameters> provider = new provider("if", "else", "and", "input", "filter", "output");

        extend(CompletionType.BASIC, patterns, provider);
    }

    private static class provider extends CompletionProvider<CompletionParameters> {
        String[] keywords;

        provider(String... keywords) {
            this.keywords = keywords;
        }

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            for (String keyword : keywords) {
                result.addElement(LookupElementBuilder.create(keyword).bold());
            }
        }
    }
}
