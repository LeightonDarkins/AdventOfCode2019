package day4

import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SecureContainerTest {
    @Test
    fun example1() {
        val testInput = "111111"

        assertTrue(SecureContainer().isPartOnePassword(testInput))
    }

    @Test
    fun example2() {
        val testInput = "223450"

        assertFalse(SecureContainer().isPartOnePassword(testInput))
    }

    @Test
    fun example3() {
        val testInput = "123789"

        assertFalse(SecureContainer().isPartOnePassword(testInput))
    }

}