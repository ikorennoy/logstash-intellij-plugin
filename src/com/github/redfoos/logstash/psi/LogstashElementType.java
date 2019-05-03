package com.github.redfoos.logstash.psi;

import com.github.redfoos.logstash.LogstashLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LogstashElementType extends IElementType {
    public LogstashElementType(@NotNull @NonNls String debugName) {
        super(debugName, LogstashLanguage.INSTANCE);
    }
}
