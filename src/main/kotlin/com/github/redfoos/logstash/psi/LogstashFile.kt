package com.github.redfoos.logstash.psi

import com.github.redfoos.logstash.LogstashFileType
import com.github.redfoos.logstash.LogstashLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class LogstashFile(viewProvider: FileViewProvider): PsiFileBase(viewProvider, LogstashLanguage.INSTANCE) {

    override fun getFileType(): FileType {
        return LogstashFileType.INSTANCE
    }

    override fun toString(): String {
        return "Logstash File"
    }
}
