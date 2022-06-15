package com.github.redfoos.logstash.execution

import com.intellij.execution.ui.CommonProgramParametersPanel
import com.intellij.execution.ui.MacroComboBoxWithBrowseButton
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.LabeledComponent
import java.awt.BorderLayout
import javax.swing.JComponent

class LogstashConfigForm : CommonProgramParametersPanel() {
    private var starterPathComponent: LabeledComponent<JComponent>? = null
    private var starterPathField: MacroComboBoxWithBrowseButton? = null

    private var filePathComponent: LabeledComponent<JComponent>? = null
    private var filePathField: MacroComboBoxWithBrowseButton? = null


    private fun initOwnComponents() {

        val chooseStarterDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
        chooseStarterDescriptor.title = "Choose Starter..."

        starterPathField = MacroComboBoxWithBrowseButton(chooseStarterDescriptor, project)
        starterPathComponent = LabeledComponent.create(starterPathField!!, "Starter path:")
        starterPathComponent?.labelLocation = BorderLayout.WEST

        val chooseScriptDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
        filePathField = MacroComboBoxWithBrowseButton(chooseScriptDescriptor, project)
        filePathComponent = LabeledComponent.create(filePathField!!, "File:")
        filePathComponent?.labelLocation = BorderLayout.WEST
    }

    override fun addComponents() {
        initOwnComponents()

        add(filePathComponent)
        add(starterPathComponent)
        add(programParametersComponent)

        super.addComponents()
    }

    fun resetForm(configuration: LogstashRunConfiguration) {
        starterPathField?.text = configuration.getStarterPath()
        filePathField?.text = configuration.getFilePath()
    }

    fun applyToForm(configuration: LogstashRunConfiguration) {
        configuration.setStarterPath(starterPathField?.text)
        configuration.setFilePath(filePathField?.text)
    }
}
