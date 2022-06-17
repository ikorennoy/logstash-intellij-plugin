package com.github.redfoos.logstash.formatting

import com.intellij.formatting.FormattingContext
import com.intellij.formatting.FormattingModel
import com.intellij.formatting.FormattingModelBuilder
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import com.intellij.psi.formatter.DocumentBasedFormattingModel

class LogstashFormattingModelBuilder: FormattingModelBuilder {

    companion object {
        fun createBlock(context: LogstashFormattingContext, node: ASTNode): LogstashFormattingBlock {
            return LogstashFormattingBlock(context, node)
        }
    }

    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val file = formattingContext.psiElement.containingFile
        val settings = formattingContext.codeStyleSettings
        val rootBlock = createBlock(LogstashFormattingContext(settings), file.node)
        return DocumentBasedFormattingModel(rootBlock, settings, file)
    }

    override fun getRangeAffectingIndent(file: PsiFile?, offset: Int, elementAtOffset: ASTNode?): TextRange? {
        return null
    }

}
