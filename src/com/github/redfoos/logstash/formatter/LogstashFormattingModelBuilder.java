//package com.github.redfoos.logstash.formatter;
//
//import com.github.redfoos.logstash.LogstashLanguage;
//import com.github.redfoos.logstash.psi.LogstashTypes;
//import com.intellij.formatting.*;
//import com.intellij.lang.ASTNode;
//import com.intellij.openapi.util.TextRange;
//import com.intellij.psi.PsiElement;
//import com.intellij.psi.PsiFile;
//import com.intellij.psi.codeStyle.CodeStyleSettings;
//import com.intellij.psi.tree.TokenSet;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//public class LogstashFormattingModelBuilder implements FormattingModelBuilder {
//    @NotNull
//    @Override
//    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
//        return FormattingModelProvider
//                .createFormattingModelForPsiFile(element.getContainingFile(),
//                        new LogstashBlock(element.getNode(),
//                                Wrap.createWrap(WrapType.NONE, false),
//                                Alignment.createAlignment(),
//                                createSpaceBuilder(settings)),
//                        settings);
//    }
//
//    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
//        return new SpacingBuilder(settings, LogstashLanguage.INSTANCE);
//    }
//
//    private static TokenSet getOneSpaceTokenSet() {
//        return TokenSet.create(LogstashTypes.RIGHTARROW, LogstashTypes.IN, LogstashTypes.OR, LogstashTypes.XOR,
//                LogstashTypes.NAND, LogstashTypes.EQUAL, LogstashTypes.NEQUAL, LogstashTypes.LESS_OR_EQUAL,
//                LogstashTypes.MORE_OR_EQUAL, LogstashTypes.LESS, LogstashTypes.MORE, LogstashTypes.REGEXPEQUAL,
//                LogstashTypes.REGEXPNEQUAL);
//    }
//
//    @Nullable
//    @Override
//    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
//        return null;
//    }
//}
