package com.github.redfoos.logstash.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LogstashFileBlock extends AbstractBlock {
    SpacingBuilder spacingBuilder;

    public LogstashFileBlock(ASTNode node, Wrap wrap, Alignment alignment, SpacingBuilder spaceBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spaceBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new LogstashPluginBlockImplBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), spacingBuilder);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public Indent getIndent() {
        return Indent.getAbsoluteNoneIndent();
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
