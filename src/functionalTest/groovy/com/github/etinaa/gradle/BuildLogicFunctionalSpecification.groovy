package com.github.etinaa.gradle

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class BuildLogicFunctionalSpecification extends Specification {

  @Rule
  final TemporaryFolder testProjectDir = new TemporaryFolder()

  File buildFile

  def setup() {
    buildFile = testProjectDir.newFile('build.gradle')
  }

  def "execute task generateCucumberReports"() {
    given:
      File reportingDir = new File(testProjectDir.newFolder('build'), 'cucumber')
      reportingDir.mkdirs()
      File json = new File(reportingDir, 'example.json')
      json << textOf('addition.json')

      buildFile << """
        plugins {
          id 'com.github.etinaa.cucumber-reporting-plugin'
        }       
    """

    when:
      def result = GradleRunner.create()
          .withProjectDir(testProjectDir.root)
          .withArguments('generateCucumberReports')
          .withPluginClasspath()
          .build()

    then:
      result.task(":generateCucumberReports").outcome == TaskOutcome.SUCCESS
      reportingDir.listFiles().size() > 0
  }

  def textOf(String fileName) {
    this.class.getResourceAsStream(fileName).text
  }
}
