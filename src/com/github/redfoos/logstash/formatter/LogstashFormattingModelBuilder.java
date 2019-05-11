package com.github.redfoos.logstash.formatter;

import com.intellij.formatting.Block;
import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.DocumentBasedFormattingModel;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiUtilCore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LogstashFormattingModelBuilder implements FormattingModelBuilder {

    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        PsiFile file = element.getContainingFile();
        Block rootBlock = createBlock(new LogstashFormattingContext(settings), element.getNode());
        return new DocumentBasedFormattingModel(rootBlock, settings, file);
    }


    public static Block createBlock(LogstashFormattingContext myContext, ASTNode subNode) {
        IElementType nodeType = PsiUtilCore.getElementType(subNode);
        return new LogstashFormattingBlock(myContext, subNode);
    }


    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}
