Gradle plugin for generating HTML Cucumber reports.
=============================
[![Travis Build Status](https://travis-ci.org/etinaa/cucumber-reporting-plugin.svg?branch=master)](https://travis-ci.org/etinaa/cucumber-reporting-plugin)
[![Download](https://api.bintray.com/packages/etinaa/gradle-plugins/cucmber-reporting-plugin/images/download.svg?version=0.2.0) ](https://bintray.com/etinaa/gradle-plugins/cucmber-reporting-plugin/0.2.0/link)

Plugin uses [cucumber-reporting](https://github.com/damianszczepanik/cucumber-reporting) version 4.2.2

Usage
-----------------------------
Add to build.gradle script:

```
buildscript {
  repositories {
    maven {
      url "https://dl.bintray.com/etinaa/gradle-plugins"
    }
  }
  dependencies {
    classpath 'com.github.etinaa:cucumber-reporting-plugin:0.2.0'
  }
}

apply plugin: 'com.github.etinaa.cucumber-reporting-plugin'

cucumberReporting {
  reportingDir = file("${project.buildDir}/cucumber")
  outputDir = file("${project.buildDir}/reports/cucumber")
}
```

Run "generateCucumberReports" task.