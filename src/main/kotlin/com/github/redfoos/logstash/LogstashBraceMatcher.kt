package com.github.redfoos.logstash

import com.github.redfoos.logstash.psi.LogstashTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class LogstashBraceMatcher : PairedBraceMatcher {
    companion object {
        val bracePairs = arrayOf(
            BracePair(LogstashTypes.LBRACE, LogstashTypes.RBRACE, false),
            BracePair(LogstashTypes.LBRACKET, LogstashTypes.RBRACKET, false),
            BracePair(LogstashTypes.LPARENTH, LogstashTypes.RPARENTH, false)
        )
    }

    override fun getPairs(): Array<BracePair> {
        return bracePairs
    }

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean {
        return true
    }

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int {
        return 0
    }
}
