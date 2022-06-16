package com.github.redfoos.logstash.execution

import com.intellij.execution.ui.CommonProgramParametersPanel
import com.intellij.execution.ui.MacroComboBoxWithBrowseButton
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.LabeledComponent
import java.awt.BorderLayout
import javax.swing.JComponent

class LogstashConfigForm : CommonProgramParametersPanel() {
    private var logstashStarterPathComponent: LabeledComponent<JComponent>? = null
    private var logstashStarterPathField: MacroComboBoxWithBrowseButton? = null

    private var configurationPathComponent: LabeledComponent<JComponent>? = null
    private var configurationPathField: MacroComboBoxWithBrowseButton? = null

    private fun initOwnComponents() {

        val chooseStarterDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
        chooseStarterDescriptor.title = "Choose Starter..."

        logstashStarterPathField = MacroComboBoxWithBrowseButton(chooseStarterDescriptor, project)
        logstashStarterPathComponent = LabeledComponent.create(logstashStarterPathField!!, "Logstash starter:")
        logstashStarterPathComponent?.labelLocation = BorderLayout.WEST

        val chooseScriptDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
        configurationPathField = MacroComboBoxWithBrowseButton(chooseScriptDescriptor, project)
        configurationPathComponent = LabeledComponent.create(configurationPathField!!, "Configuration:")
        configurationPathComponent?.labelLocation = BorderLayout.WEST
    }

    override fun addComponents() {
        initOwnComponents()

        add(configurationPathComponent)
        add(logstashStarterPathComponent)
        add(programParametersComponent)

        super.addComponents()
    }

    fun resetForm(configuration: LogstashRunConfiguration) {
        logstashStarterPathField?.text = configuration.getStarterScriptPath()
        configurationPathField?.text = configuration.getConfigurationPath()
    }

    fun applyToForm(configuration: LogstashRunConfiguration) {
        configuration.setStarterScriptPath(logstashStarterPathField?.text)
        configuration.setConfigurationPath(configurationPathField?.text)
    }
}
