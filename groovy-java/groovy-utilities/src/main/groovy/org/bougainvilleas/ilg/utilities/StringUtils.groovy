/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package org.bougainvilleas.ilg.utilities

import org.bougainvilleas.ilg.list.LinkedList

class StringUtils {
    static String join(LinkedList source) {
        return JoinUtils.join(source)
    }

    static LinkedList split(String source) {
        return SplitUtils.split(source)
    }
}