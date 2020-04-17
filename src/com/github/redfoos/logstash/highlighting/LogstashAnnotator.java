package com.github.redfoos.logstash.highlighting;

import com.github.redfoos.logstash.psi.LogstashPlugin;
import com.github.redfoos.logstash.psi.LogstashTypes;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SyntaxTraverser;
import org.jetbrains.annotations.NotNull;

public class LogstashAnnotator implements Annotator {
    private static final TextAttributesKey PLUGIN_DECLARATION = TextAttributesKey.createTextAttributesKey("LOGSTASH.PLUGIN_DECLARATION",
        DefaultLanguageHighlighterColors.CONSTANT);

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

        SyntaxTraverser.psiTraverser().withRoot(element).forEach(e1 -> {
            if (e1.getNode().getElementType() == LogstashTypes.IDENTIFIER && e1.getParent() instanceof LogstashPlugin) {
                holder.createInfoAnnotation(e1, null).setTextAttributes(PLUGIN_DECLARATION);
            }
        });
    }
}
