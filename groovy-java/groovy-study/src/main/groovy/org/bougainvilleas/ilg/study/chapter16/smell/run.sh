#!/bin/bash
groovyc -d classes CodeCheck.groovy
jar -cf codeCheck.jar -C classes org -C manifest .
groovy -cp codeCheck.jar smell.groovy
