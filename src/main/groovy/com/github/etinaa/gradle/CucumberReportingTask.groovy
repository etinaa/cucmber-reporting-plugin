package com.github.etinaa.gradle

import net.masterthought.cucumber.Configuration
import net.masterthought.cucumber.ReportBuilder
import net.masterthought.cucumber.Reportable
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

class CucumberReportingTask extends DefaultTask {

  CucumberReportingPluginExtension extension =
      (CucumberReportingPluginExtension) project.extensions.findByName(CucumberReportingPlugin.EXTENSION_NAME)

  @TaskAction
  def generateReports() {
    ReportBuilder reportBuilder = new ReportBuilder(getJsonFiles(), getConfiguration())
    Reportable result = reportBuilder.generateReports()
    if (result == null) {
      throw new GradleException('Failed to generate cucumber report html files.')
    }
  }

  List<String> getJsonFiles() {
    def jsonFiles = []
    jsonFiles
  }

  Configuration getConfiguration() {
    Configuration configuration = new Configuration(getOutputDir(), getProjectName())
    configuration.setBuildNumber(getBuildNumber())
    getClassifications().each {key, value -> configuration.addClassifications(key, value) }
    configuration
  }

  File getOutputDir() {
    File outputDir =  extension.outputDir
    if (!outputDir.exists()) {
      outputDir.mkdirs()
    }
    outputDir
  }

  String getProjectName() {
    extension.projectName
  }

  String getBuildNumber() {
    extension.buildNumber
  }

  Map<String, String> getClassifications() {
    extension.classifications ?: new HashMap<>()
  }
}
