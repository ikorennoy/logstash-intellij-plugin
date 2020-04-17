package com.github.redfoos.logstash.formatting;

import com.github.redfoos.logstash.psi.LogstashTypes;
import com.intellij.psi.tree.TokenSet;

public interface LogstashElementTypeSets {

    TokenSet LOGSTASH_BRACKETS = TokenSet.create(
        LogstashTypes.LBRACE,
        LogstashTypes.RBRACE,
        LogstashTypes.LBRACKET,
        LogstashTypes.RBRACKET,
        LogstashTypes.LPARENTH,
        LogstashTypes.RPARENTH
    );

    TokenSet AROUND_SPACES_TOKENS = TokenSet.create(
        LogstashTypes.EQUAL,
        LogstashTypes.LESS,
        LogstashTypes.MORE,
        LogstashTypes.MORE_OR_EQUAL,
        LogstashTypes.LESS_OR_EQUAL,
        LogstashTypes.REGEXPEQUAL,
        LogstashTypes.REGEXPNEQUAL,
        LogstashTypes.RIGHTARROW,
        LogstashTypes.AND,
        LogstashTypes.IN,
        LogstashTypes.NAND,
        LogstashTypes.AND,
        LogstashTypes.OR,
        LogstashTypes.XOR
    );
}
