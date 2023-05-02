package org.bougainvilleas.ilg.designpattern

def printAll(container) {
    for (item in container) {
        println item
    }
}

def numbers = [1, 2, 3, 4]
def months = [Mar: 31, Apr: 30, May: 31]
def colors = [java.awt.Color.BLACK, java.awt.Color.WHITE]
printAll numbers
printAll months
printAll colors

/**
 * The iterator pattern is also built in to other special operators
 * such as the eachByte, eachFile, eachDir, eachLine, eachObject, eachMatch operators
 * for working with streams, URLs, files, directories and regular expressions matches.
 */
colors.eachWithIndex{ item,pos->
    println "Position $pos contains '$item'"
}