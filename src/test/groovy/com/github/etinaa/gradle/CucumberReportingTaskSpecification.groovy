package com.github.etinaa.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class CucumberReportingTaskSpecification extends Specification {

  @Rule
  final TemporaryFolder testProjectDir = new TemporaryFolder()

  def "extension default properties"() {
    given:
      Project project = ProjectBuilder.builder().withProjectDir(testProjectDir.root).build()
      project.pluginManager.apply 'com.github.etinaa.cucumber-reporting'
    when:
      def extension = project.extensions.getByName(CucumberReportingPlugin.EXTENSION_NAME)
    then:
      extension.reportingDir.absolutePath == project.buildDir.absolutePath + '/cucumber'
      extension.outputDir.absolutePath == project.buildDir.absolutePath + '/reports/cucumber'
      extension.projectName == project.name
  }

  def "execute action generateReports"() {
    given:
      File reportingDir = testProjectDir.newFolder('cucumber')
      File json = new File(reportingDir, 'example.json')
      json << textOf('addition.json')

      File outputDir = testProjectDir.newFolder('reports')

      Project project = ProjectBuilder.builder().withProjectDir(testProjectDir.root).build()
      project.pluginManager.apply 'com.github.etinaa.cucumber-reporting'
      def extension = project.extensions.getByName(CucumberReportingPlugin.EXTENSION_NAME)
    when:
      extension.reportingDir = reportingDir
      extension.outputDir = outputDir
      project.tasks.getByName(CucumberReportingPlugin.TASK_NAME).generateReports()
    then:
      outputDir.listFiles().length > 0
  }

  def textOf(String fileName) {
    this.class.getResourceAsStream(fileName).text
  }
}
