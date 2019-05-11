package com.github.redfoos.logstash.formatter;

import com.github.redfoos.logstash.LogstashLanguage;
import com.github.redfoos.logstash.psi.*;
import com.github.redfoos.logstash.psi.impl.*;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.util.containers.FactoryMap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class LogstashFormattingContext {
    private final static Indent DIRECT_NORMAL_INDENT = Indent.getNormalIndent(true);
    private final static Indent SAME_AS_PARENT_INDENT = Indent.getSpaceIndent(0, true);
    private final static Indent SAME_AS_PARENT_PLUS_INDENT = Indent.getSpaceIndent(2, true);
    private final static Indent SAME_AS_INDENTED_ANCESTOR_INDENT = Indent.getSpaceIndent(0);

    @NotNull
    public final CodeStyleSettings mySettings;
    @NotNull
    private final SpacingBuilder mySpacingBuilder;

    @NotNull
    private final Map<ASTNode, Alignment> myChildIndentAlignments = FactoryMap.create(node -> Alignment.createAlignment(true));

    @NotNull
    private final Map<ASTNode, Alignment> myChildValueAlignments = FactoryMap.create(node -> Alignment.createAlignment(true));


    LogstashFormattingContext(@NotNull CodeStyleSettings settings) {
        mySettings = settings;
        mySpacingBuilder = new SpacingBuilder(mySettings, LogstashLanguage.INSTANCE);
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
                .around(LogstashTypes.RIGHTARROW)
                .spaces(1).getSpacing(parent, child1, child2);

    }

    Alignment computeAlignment(ASTNode node) {
        IElementType type = PsiUtilCore.getElementType(node);
        if (node.getPsi() instanceof LogstashPluginBlockImpl) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof LogstashPluginImpl) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof LogstashIf) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof LogstashAttributeImpl) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof LogstashElse) {
            return myChildIndentAlignments.get(node.getTreeParent());
        } else if (node.getPsi() instanceof  LogstashElseIf) {
            return myChildIndentAlignments.get(node.getTreeParent());
        }
        return null;
    }

    Indent computeBlockIndent(ASTNode node) {
        IElementType nodeType = PsiUtilCore.getElementType(node);
        IElementType parentType = PsiUtilCore.getElementType(node.getTreeParent());
        IElementType grandParentType = parentType == null ? null : PsiUtilCore.getElementType(node.getTreeParent().getTreeParent());

        if (LogstashElementTypeSets.LOGSTASH_BRACKETS.contains(nodeType)) {
            return SAME_AS_INDENTED_ANCESTOR_INDENT;
        } else if (node.getPsi() instanceof LogstashPluginImpl
                || node.getPsi() instanceof LogstashIfImpl
                || node.getPsi() instanceof LogstashElseIf
                || node.getPsi() instanceof LogstashElse
                || node.getPsi() instanceof LogstashAttributeImpl) {
            return DIRECT_NORMAL_INDENT;
        } else  {
            return SAME_AS_PARENT_INDENT;
        }
    }

    Indent computeChildIndent() {
        return DIRECT_NORMAL_INDENT;
    }


}
