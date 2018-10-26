package com.github.etinaa.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class CucumberReportingPlugin implements Plugin<Project> {

  static final String EXTENSION_NAME = 'cucumberReporting'
  static final String TASK_NAME = 'generateCucumberReports'

  @Override
  void apply(Project project) {
    project.extensions.create(EXTENSION_NAME, CucumberReportingPluginExtension, project)
    project.task(TASK_NAME, type: CucumberReportingTask)
  }
}