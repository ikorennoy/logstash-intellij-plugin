package com.github.redfoos.logstash.execution

import com.intellij.execution.ui.CommonProgramParametersPanel
import com.intellij.execution.ui.MacroComboBoxWithBrowseButton
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.ui.RawCommandLineEditor
import java.awt.BorderLayout
import javax.swing.JComponent

@Suppress("UnstableApiUsage")
class LogstashConfigForm : CommonProgramParametersPanel() {
    private var starterOptionsComponent: LabeledComponent<RawCommandLineEditor>? = null
    private var starterPathComponent: LabeledComponent<JComponent>? = null
    private var starterPathField: MacroComboBoxWithBrowseButton? = null

    private var filePathComponent: LabeledComponent<JComponent>? = null
    private var filePathField: MacroComboBoxWithBrowseButton? = null


    private fun initOwnComponents() {

        starterOptionsComponent = LabeledComponent.create(RawCommandLineEditor(), "Starter options")
        starterOptionsComponent!!.labelLocation = BorderLayout.WEST

        val chooseStarterDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
        chooseStarterDescriptor.title = "Choose Starter..."

        starterPathField = MacroComboBoxWithBrowseButton(chooseStarterDescriptor, project)
        starterPathComponent = LabeledComponent.create(starterPathField!!, "Starter path:")
        starterPathComponent!!.labelLocation = BorderLayout.WEST

        val chooseScriptDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
        filePathField = MacroComboBoxWithBrowseButton(chooseScriptDescriptor, project)
        filePathComponent = LabeledComponent.create(filePathField!!, "File:")
        filePathComponent!!.labelLocation = BorderLayout.WEST
    }

    override fun addComponents() {
        initOwnComponents()

        add(filePathComponent)
        add(starterPathComponent)
        add(starterOptionsComponent)

        super.addComponents()
    }

    fun resetForm(configuration: LogstashRunConfiguration) {
        starterOptionsComponent!!.component.text = configuration.getStarterOptions()
        starterPathField!!.text = configuration.getStarterPath()
        filePathField!!.text = configuration.getFilePath()
    }

    fun applyToForm(configuration: LogstashRunConfiguration) {
        configuration.setStarterOptions(starterOptionsComponent!!.component.text)
        configuration.setStarterPath(starterPathField!!.text)
        configuration.setFilePath(filePathField!!.text)
    }
}
