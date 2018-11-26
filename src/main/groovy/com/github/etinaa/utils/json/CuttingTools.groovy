package com.github.etinaa.utils.json

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class CuttingTools {

  static String cutHookBefore(String json) {
    def object = new JsonSlurper().parseText(json)
    object.each { e1 ->
      def elements = e1.get('elements')
      if (elements instanceof List) {
        elements.each { e2 ->
          e2.remove('before')
        }
      }
    }
    JsonOutput.toJson(object)
  }
}
