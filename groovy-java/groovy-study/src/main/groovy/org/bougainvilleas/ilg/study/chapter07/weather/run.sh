#!/bin/bash
groovyc -d classes extension/*.groovy
jar -cf weatherExtensions.jar -C classes org -C manifest .
groovy -cp weatherExtensions.jar FindWeatherTemp.groovy
