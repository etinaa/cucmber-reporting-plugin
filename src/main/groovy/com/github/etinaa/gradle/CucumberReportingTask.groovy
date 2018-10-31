package com.github.etinaa.gradle

import groovy.io.FileType
import net.masterthought.cucumber.Configuration
import net.masterthought.cucumber.ReportBuilder
import net.masterthought.cucumber.Reportable
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class CucumberReportingTask extends DefaultTask {

  CucumberReportingPluginExtension extension =
      (CucumberReportingPluginExtension) project.extensions.findByName(CucumberReportingPlugin.EXTENSION_NAME)

  @TaskAction
  def generateReports() {
    ReportBuilder reportBuilder = new ReportBuilder(getJsonFiles(), getConfiguration())
    Reportable result = reportBuilder.generateReports()
    if (result == null) {
      throw new GradleException('Failed to generate cucumber reports html files.')
    }
    println "Cucumber reports html files generated in ${outputDir.absolutePath}/cucumber-html-reports"
  }

  @Input
  List<String> getJsonFiles() {
    def jsonFiles = []
    (extension.reportingDir ?: extension.defaultReportingDir)
        .eachFileMatch(FileType.FILES, ~/.*\.json/) { jsonFiles.add(it.absolutePath) }
    jsonFiles
  }

  Configuration getConfiguration() {
    Configuration configuration = new Configuration(getOutputDir(), getProjectName())
    configuration.buildNumber = extension.buildNumber
    configuration.runWithJenkins = extension.runWithJenkins
    getClassifications().each { key, value -> configuration.addClassifications(key, value) }
    configuration
  }

  @OutputDirectory
  File getOutputDir() {
    File outputDir = extension.outputDir ?: extension.defaultOutputDir
    if (!outputDir.exists()) {
      outputDir.mkdirs()
    }
    outputDir
  }

  @Input
  String getProjectName() {
    extension.projectName ?: 'Cucumber Reporting Project'
  }

  @Input
  Map<String, String> getClassifications() {
    extension.classifications ?: [:]
  }
}
