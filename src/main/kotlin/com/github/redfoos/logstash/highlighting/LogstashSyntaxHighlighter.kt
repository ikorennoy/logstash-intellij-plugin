package com.github.redfoos.logstash.highlighting

import com.github.redfoos.logstash.LogstashLexerAdapter
import com.github.redfoos.logstash.psi.LogstashTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class LogstashSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val IDENTIFIER = createTextAttributesKey(
            "LOGSTASH_IDENTIFIER",
            DefaultLanguageHighlighterColors.IDENTIFIER
        )
        val NUMBER = createTextAttributesKey(
            "LOGSTASH_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER
        )
        val COMMENT = createTextAttributesKey(
            "LOGSTASH_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
        )
        val BAD_CHARACTER = createTextAttributesKey(
            "LOGSTASH_BAD_CHARACTER",
            HighlighterColors.BAD_CHARACTER
        )
        val KEYWORD = createTextAttributesKey(
            "LOGSTASH_KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD
        )
        val STRING = createTextAttributesKey(
            "LOGSTASH_STRING_FIELD",
            DefaultLanguageHighlighterColors.STRING
        )
        val REGEXP = createTextAttributesKey(
            "LOGSTASH_REGEXP",
            DefaultLanguageHighlighterColors.STRING
        )

        val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        val STRING_KEYS = arrayOf(STRING)
        val COMMENT_KEYS = arrayOf(COMMENT)
        val KEYWORDS_KEYS = arrayOf(KEYWORD)
        val NUMBER_KEYS = arrayOf(NUMBER)
        val REGEXP_KEYS = arrayOf(REGEXP)

        val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }

    override fun getHighlightingLexer(): Lexer {
        return LogstashLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            LogstashTypes.COMMENT -> COMMENT_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            LogstashTypes.BAREWORD -> IDENTIFIER_KEYS
            LogstashTypes.INPUT_PLUGIN_TYPE,
            LogstashTypes.FILTER_PLUGIN_TYPE,
            LogstashTypes.OUTPUT_PLUGIN_TYPE -> KEYWORDS_KEYS
            LogstashTypes.DOUBLE_QUOTED_STRING,
            LogstashTypes.SINGLE_QUOTED_STRING -> STRING_KEYS
            LogstashTypes.NUMBER -> NUMBER_KEYS
            LogstashTypes.REGEXP -> REGEXP_KEYS
            LogstashTypes.IF_OPERATOR,
            LogstashTypes.ELSE_OPERATOR,
            LogstashTypes.AND_OPERATOR,
            LogstashTypes.IN_OPERATOR,
            LogstashTypes.NOT_OPERATOR,
            LogstashTypes.OR_OPERATOR,
            LogstashTypes.XOR_OPERATOR,
            LogstashTypes.NAND_OPERATOR -> KEYWORDS_KEYS
            else -> EMPTY_KEYS
        }
    }
}
