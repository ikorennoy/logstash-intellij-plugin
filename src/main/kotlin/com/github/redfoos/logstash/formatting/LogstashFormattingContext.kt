package com.github.redfoos.logstash.formatting

import com.github.redfoos.logstash.LogstashLanguage
import com.github.redfoos.logstash.psi.LogstashTypes
import com.github.redfoos.logstash.psi.impl.LogstashAttributeImpl
import com.github.redfoos.logstash.psi.impl.LogstashBranchImpl
import com.github.redfoos.logstash.psi.impl.LogstashHashEntryImpl
import com.github.redfoos.logstash.psi.impl.LogstashHashImpl
import com.github.redfoos.logstash.psi.impl.LogstashPluginImpl
import com.github.redfoos.logstash.psi.impl.LogstashPluginSectionImpl
import com.intellij.formatting.ASTBlock
import com.intellij.formatting.Alignment
import com.intellij.formatting.Block
import com.intellij.formatting.Indent
import com.intellij.formatting.Spacing
import com.intellij.formatting.SpacingBuilder
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.util.PsiUtilCore
import com.intellij.util.containers.FactoryMap
import java.util.*

class LogstashFormattingContext(private val codeStyleSettings: CodeStyleSettings) {

    private val DIRECT_NORMAL_INDENT = Indent.getNormalIndent(true)
    private val UDIRECT_NORMAL_INDENT = Indent.getNormalIndent(false)
    private val SAME_AS_ANCESTOR_INDENT = Indent.getSpaceIndent(0)


    private val mySpacingBuilder: SpacingBuilder = SpacingBuilder(codeStyleSettings, LogstashLanguage.INSTANCE)
    private val myChildIndentAlignments: Map<ASTNode, Alignment> =
        FactoryMap.create { _ -> Alignment.createAlignment(true) }

    fun computeSpacing(parent: Block, child1: Block?, child2: Block): Spacing? {
        val spacing = mySpacingBuilder.getSpacing(parent, child1, child2)
        if (spacing != null) {
            return spacing
        }
        if (!(child1 is ASTBlock && child2 is ASTBlock)) {
            return null
        }
        return mySpacingBuilder.before(LogstashTypes.LBRACE)
            .spaces(1)
            .around(LogstashElementTypeSets.AROUND_SPACES_TOKENS)
            .spaces(1).getSpacing(parent, child1, child2)
    }

    fun computeAlignment(node: ASTNode): Alignment? {
        val alignment = when (node.psi) {
            is LogstashPluginSectionImpl -> myChildIndentAlignments[node.treeParent]
            is LogstashAttributeImpl -> myChildIndentAlignments[node.treeParent]
            is LogstashBranchImpl -> myChildIndentAlignments[node.treeParent]
            is LogstashHashEntryImpl -> myChildIndentAlignments[node.treeParent]
            else -> null
        }
        return alignment
    }


    fun computeBlockIndent(node: ASTNode): Indent? {
        val nodeType = PsiUtilCore.getElementType(node)
        val indent = if (LogstashElementTypeSets.LOGSTASH_SAME_INDENT.contains(nodeType)) {
            SAME_AS_ANCESTOR_INDENT
        } else if (node.psi is LogstashPluginImpl || node.psi is LogstashAttributeImpl || node.psi is LogstashBranchImpl || node.psi is LogstashHashImpl) {
            DIRECT_NORMAL_INDENT
        } else if (Objects.equals(PsiUtilCore.getElementType(node.treeParent), LogstashTypes.ARRAY)) {
            if (LogstashElementTypeSets.LOGSTASH_SAME_INDENT.contains(nodeType)) {
                SAME_AS_ANCESTOR_INDENT
            } else {
                UDIRECT_NORMAL_INDENT
            }
        } else if (node.psi is PsiComment) {
            SAME_AS_ANCESTOR_INDENT
        } else {
            null
        }
        return indent
    }

    fun computeChildIndent(): Indent {
        return DIRECT_NORMAL_INDENT
    }

}
