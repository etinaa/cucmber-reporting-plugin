package com.github.etinaa.gradle


import groovy.json.JsonOutput
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class CutHookBeforeSpecification extends Specification {

  @Rule
  final TemporaryFolder testProjectDir = new TemporaryFolder()

  File buildFile

  def setup() {
    buildFile = testProjectDir.newFile('build.gradle')
  }

  def "execute task generateCucumberReports with option 'cutHookBefore'"() {
    given:
      File reportingDir = new File(testProjectDir.newFolder('build'), 'cucumber')
      reportingDir.mkdirs()
      File json = new File(reportingDir, 'example.json')
      json << textOf('multiplication.json')

      buildFile << """
        plugins {
          id 'com.github.etinaa.cucumber-reporting-plugin'
        }
        
        cucumberReporting {
          options {
            cutHookBefore = true  
          }
        }       
      """

      def expected = JsonOutput.prettyPrint(textOf('multiplication_hook_before_cutted.json'))
    when:
      def result = GradleRunner.create()
          .withProjectDir(testProjectDir.root)
          .withArguments('generateCucumberReports')
          .withPluginClasspath()
          .build()

      def extFile = reportingDir.listFiles().find { it.isDirectory() && it.name == 'ext' }
          .listFiles().find { it.isFile() && it.name == 'example_ext.json' }
      def actual = JsonOutput.prettyPrint(extFile.text)
    then:
      result.task(":generateCucumberReports").outcome == TaskOutcome.SUCCESS
      actual == expected
  }

  def textOf(String fileName) {
    this.class.getResourceAsStream(fileName).text
  }
}
