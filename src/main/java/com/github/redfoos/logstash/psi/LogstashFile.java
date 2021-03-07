package com.github.redfoos.logstash.psi;

import com.github.redfoos.logstash.LogstashFileType;
import com.github.redfoos.logstash.LogstashLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class LogstashFile extends PsiFileBase {
    public LogstashFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, LogstashLanguage.getINSTANCE());
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return LogstashFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Logstash File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
