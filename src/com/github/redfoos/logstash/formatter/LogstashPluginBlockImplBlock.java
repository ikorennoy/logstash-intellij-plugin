package com.github.redfoos.logstash.formatter;

import com.github.redfoos.logstash.psi.impl.LogstashPluginImpl;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LogstashPluginBlockImplBlock extends AbstractBlock {
    SpacingBuilder spacingBuilder;

    public LogstashPluginBlockImplBlock(ASTNode child, Wrap wrap, Alignment alignment, SpacingBuilder spaceBuilder) {
        super(child, wrap, alignment);
        this.spacingBuilder = spaceBuilder;
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        return new ChildAttributes(Indent.getSpaceIndent(4), null);
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                if (child.getPsi() instanceof LogstashPluginImpl) {
                    Block block = new LogstashPluginImplBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), spacingBuilder);
                    blocks.add(block);
                } else {
                    Block block = new LogstashPluginBlockImplBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), spacingBuilder);
                    blocks.add(block);
                }
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        return Indent.getAbsoluteNoneIndent();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
