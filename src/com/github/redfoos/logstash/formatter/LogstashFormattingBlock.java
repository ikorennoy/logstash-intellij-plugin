package com.github.redfoos.logstash.formatter;

import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiUtilCore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LogstashFormattingBlock extends AbstractBlock {
    private final LogstashFormattingContext myContext;

    private final Indent myIndent;

    private final Indent myNewChildIndent;


    LogstashFormattingBlock(LogstashFormattingContext context, ASTNode node) {
        super(node, null, context.computeAlignment(node));
        myContext = context;
        myNewChildIndent = myContext.computeChildIndent();
        myIndent = myContext.computeBlockIndent(myNode);

    }

    @Nullable
    @Override
    protected Indent getChildIndent() {
        return myNewChildIndent;
    }

    @Override
    protected List<Block> buildChildren() {
        return buldSubBlocks(myContext, myNode);
    }

    private List<Block> buldSubBlocks(LogstashFormattingContext myContext, ASTNode node) {
        List<Block> res = new ArrayList<>();
        for (ASTNode subNode = node.getFirstChildNode(); subNode != null; subNode = subNode.getTreeNext()) {
            IElementType subNodeType = PsiUtilCore.getElementType(subNode);
            if (subNodeType != TokenType.WHITE_SPACE) {
                res.add(LogstashFormattingModelBuilder.createBlock(myContext, subNode));
            }
        }
        return res;
    }

    @Override
    public Indent getIndent() {
        return myIndent;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return myContext.computeSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
