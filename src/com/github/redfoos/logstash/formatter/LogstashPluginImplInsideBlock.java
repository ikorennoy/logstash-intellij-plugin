package com.github.redfoos.logstash.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LogstashPluginImplInsideBlock extends AbstractBlock {
    private SpacingBuilder spacingBuilder;
    static Alignment alignment = Alignment.createAlignment();

    public LogstashPluginImplInsideBlock(ASTNode child, Wrap wrap, Alignment alignment, SpacingBuilder spacingBuilder) {
        super(child, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    public Indent getIndent() {
        return Indent.getSpaceIndent(4, false);
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        return blocks;
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
