package com.github.redfoos.logstash.formatting

import com.github.redfoos.logstash.psi.LogstashTypes
import com.intellij.psi.tree.TokenSet

interface LogstashElementTypeSets {
    companion object {
        val LOGSTASH_SAME_INDENT = TokenSet.create(
            LogstashTypes.LBRACE,
            LogstashTypes.RBRACE,
            LogstashTypes.LBRACKET,
            LogstashTypes.RBRACKET,
            LogstashTypes.LPARENTH,
            LogstashTypes.RPARENTH,
        )

        val AROUND_SPACES_TOKENS = TokenSet.create(
            LogstashTypes.EQUALS,
            LogstashTypes.LESS_THAN,
            LogstashTypes.GREATER_THAN,
            LogstashTypes.GREATER_THAN_OR_EQUAL,
            LogstashTypes.LESS_THAN_OR_EQUAL,
            LogstashTypes.REGEXP_EQUAL,
            LogstashTypes.REGEXP_NOT_EQUAL,
            LogstashTypes.RIGHT_ARROW,
            LogstashTypes.AND_OPERATOR,
            LogstashTypes.IN_OPERATOR,
            LogstashTypes.NAND_OPERATOR,
            LogstashTypes.AND_OPERATOR,
            LogstashTypes.OR_OPERATOR,
            LogstashTypes.XOR_OPERATOR
        )
    }
}
