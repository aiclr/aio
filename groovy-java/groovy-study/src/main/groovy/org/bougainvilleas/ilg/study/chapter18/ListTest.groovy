package org.bougainvilleas.ilg.study.chapter18

import groovy.test.GroovyTestCase

class ListTest extends GroovyTestCase{
    void testListSize(){
        def lst=[1,2]
        assertEquals "ArrayList size must be 2",2,lst.size()
    }
}
