package com.github.etinaa.utils.json

import groovy.json.JsonOutput
import spock.lang.Specification

class CuttingToolsSpecification extends Specification {

  def "Cut hook 'before' from json report"() {
    given:
      def json = textOf('multiplication.json')
      def expected = JsonOutput.prettyPrint(textOf('multiplication_hook_before_cutted.json'))
    when:
      def result = CuttingTools.cutHookBefore(json)
      def actual = JsonOutput.prettyPrint(result)
    then:
      actual == expected
  }

  def textOf(String fileName) {
    this.class.getResourceAsStream(fileName).text
  }
}
