package com.github.redfoos.logstash.execution

import com.github.redfoos.logstash.psi.impl.LogstashPluginBlockImpl
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.psi.PsiElement

class LogstashRunLineMarkerContributor : RunLineMarkerContributor() {

    override fun getInfo(element: PsiElement): Info? {

        if (element is LogstashPluginBlockImpl) {
            val actions: Array<AnAction> = ExecutorAction.getActions(0)
            val editConfigs = ActionManager.getInstance().getAction("editRunConfigurations")
            return Info(
                AllIcons.RunConfigurations.TestState.Run,
                { "Run configuration" },
                actions[0],
                editConfigs
            )
        }
        return null
    }
}
