package com.github.redfoos.logstash.formatter;

import com.github.redfoos.logstash.psi.LogstashTypes;
import com.github.redfoos.logstash.psi.impl.LogstashPluginImpl;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LogstashPluginImplBlock extends AbstractBlock {
    private SpacingBuilder spacingBuilder;
    static Alignment alignment = Alignment.createAlignment();

    public LogstashPluginImplBlock(ASTNode child, Wrap wrap, Alignment alignment, SpacingBuilder spacingBuilder) {
        super(child, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    public Indent getIndent() {
        return Indent.getNoneIndent();
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {

        return new ChildAttributes(Indent.getSpaceIndent(8), null);
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new LogstashPluginImplInsideBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), spacingBuilder);
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
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
