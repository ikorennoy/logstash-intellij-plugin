package com.github.redfoos.logstash;

import com.github.redfoos.logstash.psi.LogstashTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LogstashSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final TextAttributesKey IDENTIFIER =
        createTextAttributesKey("LOGSTASH_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    private static final TextAttributesKey NUM =
        createTextAttributesKey("LOGSTASH_VALUE", DefaultLanguageHighlighterColors.NUMBER);
    private static final TextAttributesKey COMMENT =
        createTextAttributesKey("LOGSTASH_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    private static final TextAttributesKey BAD_CHARACTER =
        createTextAttributesKey("LOGSTASH_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    private static final TextAttributesKey KEYWORD =
        createTextAttributesKey("LOGSTASH_DECLARATION", DefaultLanguageHighlighterColors.KEYWORD);
    private static final TextAttributesKey STRING =
        createTextAttributesKey("LOGSTASH_STATIC_FIELD", DefaultLanguageHighlighterColors.STRING);
    private static final TextAttributesKey REGEXP =
        createTextAttributesKey("LOGSTASH_REGEX", DefaultLanguageHighlighterColors.STRING);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] NUM_KEYS = new TextAttributesKey[]{NUM};
    private static final TextAttributesKey[] REGEXP_KEYS = new TextAttributesKey[]{REGEXP};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new LogstashLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(LogstashTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else if (tokenType.equals(LogstashTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        } else if (tokenType.equals(LogstashTypes.PLUGIN_BLOCK)) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(LogstashTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(LogstashTypes.NUMBER)) {
            return NUM_KEYS;
        } else if (tokenType.equals(LogstashTypes.REGEXP)) {
            return REGEXP_KEYS;
        } else if (tokenType.equals(LogstashTypes.IF_TOK) ||
            tokenType.equals(LogstashTypes.ELSE_TOK) ||
            tokenType.equals(LogstashTypes.AND) ||
            tokenType.equals(LogstashTypes.IN) ||
            tokenType.equals(LogstashTypes.NOT) ||
            tokenType.equals(LogstashTypes.OR) ||
            tokenType.equals(LogstashTypes.XOR) ||
            tokenType.equals(LogstashTypes.NAND)) {
            return KEYWORD_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
