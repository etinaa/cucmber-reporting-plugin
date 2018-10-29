package com.github.etinaa.gradle

import org.gradle.api.Project

class CucumberReportingPluginExtension {

  static final String DEFAULT_REPORTING_DIR = 'cucumber'
  static final String DEFAULT_OUTPUT_DIR = 'reports/cucumber'

  Project project

  File reportingDir
  File outputDir
  String projectName
  String buildNumber
  boolean runWithJenkins = false
  Map<String, String> classifications

  CucumberReportingPluginExtension(Project project) {
    this.project = project
    reportingDir = defaultReportingDir
    outputDir = defaultOutputDir
  }

  File getDefaultReportingDir() {
    new File(project.buildDir, DEFAULT_REPORTING_DIR)
  }

  File getDefaultOutputDir() {
    new File(project.buildDir, DEFAULT_OUTPUT_DIR)
  }

  String getProjectName() {
    projectName ?: project.name
  }
}
