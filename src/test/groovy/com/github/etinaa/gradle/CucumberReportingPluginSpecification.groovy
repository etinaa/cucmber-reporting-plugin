package com.github.etinaa.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class CucumberReportingPluginSpecification extends Specification {

  def "applying plugin"() {
    given:
      Project project = ProjectBuilder.builder().build()
    when:
      project.pluginManager.apply 'com.github.etinaa.cucumber-reporting-plugin'
    then:
      project.tasks.getByName(CucumberReportingPlugin.TASK_NAME)
      project.tasks.generateCucumberReports instanceof CucumberReportingTask
      project.extensions.getByName(CucumberReportingPlugin.EXTENSION_NAME)
  }
}
