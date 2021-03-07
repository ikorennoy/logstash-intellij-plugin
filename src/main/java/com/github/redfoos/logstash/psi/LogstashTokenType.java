package com.github.redfoos.logstash.psi;

import com.github.redfoos.logstash.LogstashLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LogstashTokenType extends IElementType {
    public LogstashTokenType(@NotNull @NonNls String debugName) {
        super(debugName, LogstashLanguage.getINSTANCE());
    }

    @Override
    public String toString() {
        return "Logstash token type." + super.toString();
    }
}
