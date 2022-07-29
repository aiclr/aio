#!/bin/bash
groovyc -d classes InjectAudit.groovy
jar -cf injectAudit.jar -C classes org -C manifest .
groovy -cp injectAudit.jar UsingCheckingAccount.groovy
