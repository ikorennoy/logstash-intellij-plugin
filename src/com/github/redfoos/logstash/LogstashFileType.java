package com.github.redfoos.logstash;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LogstashFileType extends LanguageFileType {
    public static final LanguageFileType INSTANCE = new LogstashFileType();
    @NonNls public static final String DEFAULT_EXTENSION = "conf";
    @NonNls public static final String DOT_DEFAULT_EXTENSION = "." + DEFAULT_EXTENSION;

    private LogstashFileType() {
        super(LogstashLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Logstash";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Logstash conf file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/icons/logstash.png", LogstashFileType.class);
    }
}
