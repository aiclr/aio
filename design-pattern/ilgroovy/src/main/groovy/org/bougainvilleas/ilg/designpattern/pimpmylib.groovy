package org.bougainvilleas.ilg.designpattern

/**
 * Pimp my Library Pattern
 * 利用 Category 扩展 java.lang.Integer
 */
class EnhancedInteger {
    static boolean greaterThanAll(Integer self, Object[] others) {
        greaterThanAll(self, others as AbstractList)
    }

    static boolean greaterThanAll(Integer self, others) {
        others.every { self > it }
    }
}

use(EnhancedInteger) {
    assert 4.greaterThanAll(1, 2, 3)
    assert !5.greaterThanAll(2, 4, 6)
    assert 5.greaterThanAll(-4..4)
    assert 5.greaterThanAll([])
    assert !5.greaterThanAll([4, 5])
}