package com.github.etinaa.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class CucumberReportPluginSpecification extends Specification {

  def "applying plugin"() {
    given:
      Project project = ProjectBuilder.builder().build()
    when:
      project.pluginManager.apply 'com.github.etinaa.cucumber-reporting'
    then:
      project.tasks.generateCucumberReports instanceof CucumberReportingTask
  }
}
