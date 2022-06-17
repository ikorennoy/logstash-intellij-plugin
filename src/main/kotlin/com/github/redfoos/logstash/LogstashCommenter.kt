package com.github.redfoos.logstash

import com.intellij.lang.Commenter

class LogstashCommenter: Commenter {

    override fun getLineCommentPrefix(): String = "#"

    override fun getBlockCommentPrefix(): String? = null

    override fun getBlockCommentSuffix(): String? = null

    override fun getCommentedBlockCommentPrefix(): String? = null

    override fun getCommentedBlockCommentSuffix(): String? = null
}
