package com.github.redfoos.logstash.formatter;

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

}