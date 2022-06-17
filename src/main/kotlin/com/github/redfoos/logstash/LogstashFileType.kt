package com.github.redfoos.logstash

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class LogstashFileType: LanguageFileType(LogstashLanguage.INSTANCE) {
    companion object {
        const val DEFAULT_EXTENSION = "conf"
        val INSTANCE = LogstashFileType()
    }

    override fun getName(): String = "Logstash"

    override fun getDescription(): String = "Logstash configuration file"

    override fun getDefaultExtension(): String = DEFAULT_EXTENSION

    override fun getIcon(): Icon = Icons.logstash
}
