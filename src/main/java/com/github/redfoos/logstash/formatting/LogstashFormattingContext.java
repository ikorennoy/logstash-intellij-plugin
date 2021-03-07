package com.github.redfoos.logstash.formatting;

import com.github.redfoos.logstash.LogstashLanguage;
import com.github.redfoos.logstash.psi.LogstashTypes;
import com.github.redfoos.logstash.psi.impl.*;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.util.containers.FactoryMap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class LogstashFormattingContext {
    private final static Indent DIRECT_NORMAL_INDENT = Indent.getNormalIndent(true);
    private final static Indent UNDIRECT_NORMAL_INDENT = Indent.getNormalIndent(false);
    private final static Indent SAME_AS_INDENTED_ANCESTOR_INDENT = Indent.getSpaceIndent(0);

    @NotNull
    private final CodeStyleSettings mySettings;
    @NotNull
    private final SpacingBuilder mySpacingBuilder;

    @NotNull
    private final Map<ASTNode, Alignment> myChildIndentAlignments = FactoryMap.create(node -> Alignment.createAlignment(true));

    LogstashFormattingContext(@NotNull CodeStyleSettings settings) {
        mySettings = settings;
        mySpacingBuilder = new SpacingBuilder(mySettings, LogstashLanguage.getINSTANCE());
    }

    Spacing computeSpacing(Block parent, Block child1, Block child2) {
        Spacing simpleSpacing = mySpacingBuilder.getSpacing(parent, child1, child2);
        if (simpleSpacing != null) {
            return simpleSpacing;
        }
        if (!(child1 instanceof ASTBlock && child2 instanceof ASTBlock)) {
            return null;
        }

        return mySpacingBuilder.before(LogstashTypes.LBRACE)
            .spaces(1)
            .around(LogstashElementTypeSets.AROUND_SPACES_TOKENS)
            .spaces(1).getSpacing(parent, child1, child2);
    }

    Alignment computeAlignment(ASTNode node) {
        if (node.getPsi() instanceof LogstashPluginBlockImpl) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof LogstashPluginImpl) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof LogstashAttributeImpl) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof LogstashBranchImpl) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof LogstashHashentryImpl) {
            return myChildIndentAlignments.get(node.getTreeParent());
        }
        return null;
    }

    Indent computeBlockIndent(ASTNode node) {
        IElementType nodeType = PsiUtilCore.getElementType(node);
        if (Objects.equals(PsiUtilCore.getElementType(node.getTreeParent()), LogstashTypes.ARRAY)) {
            if (LogstashElementTypeSets.LOGSTASH_SAME_INDENT.contains(nodeType)) {
                return SAME_AS_INDENTED_ANCESTOR_INDENT;
            }
            return UNDIRECT_NORMAL_INDENT;
        }
        if (LogstashElementTypeSets.LOGSTASH_SAME_INDENT.contains(nodeType)) {
            return SAME_AS_INDENTED_ANCESTOR_INDENT;
        } else if (node.getPsi() instanceof LogstashPluginImpl ||
            node.getPsi() instanceof LogstashAttributeImpl ||
            node.getPsi() instanceof LogstashBranchImpl ||
            node.getPsi() instanceof LogstashHashentryImpl) {
            return DIRECT_NORMAL_INDENT;
        } else {
            return null;
        }
    }

    Indent computeChildIndent() {
        return DIRECT_NORMAL_INDENT;
    }
}
