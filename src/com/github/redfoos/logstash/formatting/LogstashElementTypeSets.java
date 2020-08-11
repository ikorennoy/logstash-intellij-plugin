package com.github.redfoos.logstash.formatting;

import com.intellij.psi.tree.TokenSet;

import static com.github.redfoos.logstash.psi.LogstashTypes.*;

public interface LogstashElementTypeSets {

    TokenSet LOGSTASH_BRACKETS = TokenSet.create(
        LBRACE,
        RBRACE,
        LBRACKET,
        RBRACKET,
        LPARENTH,
        RPARENTH,
        COMMENT
    );

    TokenSet AROUND_SPACES_TOKENS = TokenSet.create(
        EQUAL,
        LESS,
        MORE,
        MORE_OR_EQUAL,
        LESS_OR_EQUAL,
        REGEXPEQUAL,
        REGEXPNEQUAL,
        RIGHTARROW,
        AND,
        IN,
        NAND,
        AND,
        OR,
        XOR
    );
}
