package com.github.redfoos.logstash.formatting;

import com.intellij.psi.tree.TokenSet;

import static com.github.redfoos.logstash.psi.LogstashTypes.*;

public interface LogstashElementTypeSets {

    TokenSet LOGSTASH_SAME_INDENT = TokenSet.create(
        LBRACE,
        RBRACE,
        LBRACKET,
        RBRACKET,
        LPARENTH,
        RPARENTH,
        COMMENT
    );

    TokenSet AROUND_SPACES_TOKENS = TokenSet.create(
        EQUALS,
        LESS_THAN,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        LESS_THAN_OR_EQUAL,
        REGEXP_EQUAL,
        REGEXP_NOT_EQUAL,
        RIGHT_ARROW,
        AND_OPERATOR,
        IN_OPERATOR,
        NAND_OPERATOR,
        AND_OPERATOR,
        OR_OPERATOR,
        XOR_OPERATOR
    );
}
