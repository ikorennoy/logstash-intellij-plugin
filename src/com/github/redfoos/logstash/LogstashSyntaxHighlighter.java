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
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("LOGSTASH_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("LOGSTASH_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey NUM =
            createTextAttributesKey("LOGSTASH_VALUE", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("LOGSTASH_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("LOGSTASH_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("LOGSTASH_DECLARATION", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("LOGSTASH_STATIC_FIELD", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey DEFAULT_COMMA =
            createTextAttributesKey("LOGSTASH_COMMA", DefaultLanguageHighlighterColors.COMMA);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] NUM_KEYS = new TextAttributesKey[]{NUM};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{DEFAULT_COMMA};

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
        } else if (tokenType.equals(LogstashTypes.COMMA)) {
            return COMMA_KEYS;
        } else if ( (tokenType.equals(LogstashTypes.LBRACE)) || (tokenType.equals(LogstashTypes.RBRACE)) || (tokenType.equals(LogstashTypes.RIGHTARROW)) ) {
            return SEPARATOR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
