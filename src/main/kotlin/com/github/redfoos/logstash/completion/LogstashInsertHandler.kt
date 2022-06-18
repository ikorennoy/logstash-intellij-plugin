package com.github.redfoos.logstash.completion

import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler
import com.intellij.codeInsight.lookup.LookupElement

abstract class LogstashInsertHandler private constructor(
    spaceBefore: Boolean,
    spaceBetween: Boolean,
    mayInsertRight: Boolean,
    allowNextLine: Boolean,
    left: Char,
    right: Char,
) : ParenthesesInsertHandler<LookupElement>(
    spaceBefore,
    spaceBetween,
    mayInsertRight,
    allowNextLine,
    left,
    right
) {

    companion object {
        val BRACE = object : LogstashInsertHandler(
            spaceBefore = true,
            spaceBetween = false,
            mayInsertRight = true,
            allowNextLine = true,
            left = '{',
            right = '}'
        ) {
            override fun placeCaretInsideParentheses(context: InsertionContext?, item: LookupElement?): Boolean {
                return true
            }

        }

        val ARROW = object : LogstashInsertHandler(
            spaceBefore = true,
            spaceBetween = false,
            mayInsertRight = true,
            allowNextLine = false,
            left = '=',
            right = '>'
        ) {
            override fun placeCaretInsideParentheses(context: InsertionContext?, item: LookupElement?): Boolean {
                return false
            }

        }
    }
}
