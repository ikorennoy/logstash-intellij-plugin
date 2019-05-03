package com.github.redfoos.logstash;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NonNls;

public class LogstashFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NonNls FileTypeConsumer consumer) {
        consumer.consume(LogstashFileType.INSTANCE, LogstashFileType.DEFAULT_EXTENSION);
    }
}
