package com.github.redfoos.logstash.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogstashConfigBlock extends AbstractBlock {
    private SpacingBuilder spacingBuilder;

    protected LogstashConfigBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                            SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }
    @Override
    protected List<Block> buildChildren() {
        return null;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }


    @Override
    public boolean isLeaf() {
        return false;
    }
}
