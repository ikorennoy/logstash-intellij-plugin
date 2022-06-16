package com.github.redfoos.logstash.execution

import com.intellij.openapi.options.SettingsEditor
import javax.swing.JComponent

class LogstashSettingsEditor : SettingsEditor<LogstashRunConfiguration>() {
    private val form = LogstashConfigForm()

    override fun resetEditorFrom(runConfiguration: LogstashRunConfiguration) {
        form.reset(runConfiguration)
        form.resetForm(runConfiguration)
    }

    override fun createEditor(): JComponent = form

    override fun applyEditorTo(runConfiguration: LogstashRunConfiguration) {
        form.applyTo(runConfiguration)
        form.applyToForm(runConfiguration)
    }
}
