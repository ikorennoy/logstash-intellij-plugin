package com.github.redfoos.logstash.formatting

import com.intellij.formatting.Block
import com.intellij.formatting.Indent
import com.intellij.formatting.Spacing
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import com.intellij.psi.util.PsiUtilCore

class LogstashFormattingBlock(
    private val myContext: LogstashFormattingContext,
    myNode: ASTNode
) : AbstractBlock(myNode, null, myContext.computeAlignment(myNode)) {

    private val myIndent: Indent?
    private val myNewChildIndent: Indent

    init {
        myIndent = myContext.computeBlockIndent(myNode)
        myNewChildIndent = myContext.computeChildIndent()
    }

    override fun getIndent(): Indent? {
        return myIndent
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return myContext.computeSpacing(this, child1, child2)
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }

    override fun buildChildren(): MutableList<Block> {
        val result = mutableListOf<Block>()
        var subNode = myNode.firstChildNode
        while (subNode != null) {
            val type = PsiUtilCore.getElementType(subNode)
            if (type != TokenType.WHITE_SPACE) {
                result.add(LogstashFormattingModelBuilder.createBlock(myContext, subNode))
            }
            subNode = subNode.treeNext
        }
        return result
    }

    override fun getChildIndent(): Indent {
        return myNewChildIndent
    }
}
