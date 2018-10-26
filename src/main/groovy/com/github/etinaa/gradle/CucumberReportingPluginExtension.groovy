package com.github.etinaa.gradle

import org.gradle.api.Project

class CucumberReportingPluginExtension {

  static final String DEFAULT_REPORTING_DIR = 'cucumber'
  static final String DEFAULT_OUTPUT_DIR = 'reports/cucumber'

  File reportingDir
  File outputDir
  String projectName
  String buildNumber
  Map<String, String> classifications

  CucumberReportingPluginExtension(Project project) {
    reportingDir = new File(project.buildDir, DEFAULT_REPORTING_DIR)
    outputDir = new File(project.buildDir, DEFAULT_OUTPUT_DIR)
    projectName = project.name
    classifications = new HashMap<>()
  }
}
