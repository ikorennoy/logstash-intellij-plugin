package com.github.redfoos.logstash.completion

import com.github.redfoos.logstash.LogstashLanguage
import com.github.redfoos.logstash.psi.LogstashTypes
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext

class LogstashKeywordCompletionContributor : CompletionContributor() {
    private val psiElementCapture = PlatformPatterns.psiElement().withLanguage(LogstashLanguage.INSTANCE)
    private val ifPattern = PlatformPatterns.psiElement(LogstashTypes.IF_TOK)
    private val elsePattern = PlatformPatterns.psiElement(LogstashTypes.ELSE_TOK)
    private val andPattern = PlatformPatterns.psiElement(LogstashTypes.AND)
    private val pluginBlockPattern = PlatformPatterns.psiElement(LogstashTypes.PLUGIN_BLOCK)

    init {
        val patterns = PlatformPatterns.or(psiElementCapture, ifPattern, elsePattern, andPattern, pluginBlockPattern)
        val provider = LogstashCompletionProvider(listOf("if", "else", "and", "input", "filter", "output"))
        extend(CompletionType.BASIC, patterns, provider)
    }
}

class LogstashCompletionProvider(private val keywords: List<String>) : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        for (keyword in keywords) {
            result.addElement(LookupElementBuilder.create(keyword).bold())
        }
    }
}
