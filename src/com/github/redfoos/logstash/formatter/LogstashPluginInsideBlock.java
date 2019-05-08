package com.github.redfoos.logstash.formatter;

import com.github.redfoos.logstash.psi.LogstashTypes;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LogstashPluginInsideBlock extends AbstractBlock {
    private final SpacingBuilder spacingBuilder;
    private final static Alignment alignment = Alignment.createAlignment();
    protected LogstashPluginInsideBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Nullable
    @Override
    protected Indent getChildIndent() {
        return Indent.getNoneIndent();
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
                if (child.getElementType() == LogstashTypes.IDENTIFIER || child.getElementType() == LogstashTypes.LBRACE || child.getElementType() == LogstashTypes.RBRACE) {
                    Block block = new LogstashPluginInsideBlock(child, Wrap.createWrap(WrapType.NONE, true), Alignment.createAlignment(), spacingBuilder);
                    blocks.add(block);
                }
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        return Indent.getSpaceIndent(4, false);
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
