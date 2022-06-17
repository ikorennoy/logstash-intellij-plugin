package com.github.redfoos.logstash

import com.github.redfoos.logstash.psi.LogstashFile
import com.github.redfoos.logstash.psi.LogstashTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class LogstashParserDefinition: ParserDefinition {
    companion object {
        val COMMENTS = TokenSet.create(LogstashTypes.COMMENT)
        val FILE = IFileElementType(LogstashLanguage.INSTANCE)
        val STRING_LITERALS = TokenSet.create(LogstashTypes.SINGLE_QUOTED_STRING, LogstashTypes.DOUBLE_QUOTED_STRING)
    }

    override fun createLexer(project: Project?): Lexer = LogstashLexerAdapter()

    override fun createParser(project: Project?): PsiParser = LogstashParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = STRING_LITERALS

    override fun createElement(node: ASTNode?): PsiElement = LogstashTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = LogstashFile(viewProvider)

}
