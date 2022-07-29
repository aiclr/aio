#!/bin/bash
groovyc -d classes EAM.groovy EAMTransformation.groovy
jar -cf eam.jar -C classes org
groovy -cp eam.jar resource.groovy
