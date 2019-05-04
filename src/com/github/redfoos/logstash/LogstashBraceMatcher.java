package com.github.redfoos.logstash;

import com.github.redfoos.logstash.psi.LogstashTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LogstashBraceMatcher implements PairedBraceMatcher {
    private static BracePair[] pairs = new BracePair[3];

    static {
        pairs[0] = new BracePair(LogstashTypes.LBRACE, LogstashTypes.RBRACE, false);
        pairs[1] = new BracePair(LogstashTypes.LBRACKET, LogstashTypes.RBRACKET, false);
        pairs[2] = new BracePair(LogstashTypes.LPARENTH, LogstashTypes.RPARENTH, false);
    }

    @NotNull
    @Override
    public BracePair[] getPairs() {
        return pairs;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return 0;
    }
}
