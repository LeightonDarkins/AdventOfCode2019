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

    @Test
    fun partTwo_example1() {
        val testInput = "112233"

        assertTrue(SecureContainer().isPartTwoPassword(testInput))
    }

    @Test
    fun partTwo_example2() {
        val testInput = "123444"

        assertFalse(SecureContainer().isPartTwoPassword(testInput))
    }

    @Test
    fun partTwo_example3() {
        val testInput = "111122"

        assertTrue(SecureContainer().isPartTwoPassword(testInput))
    }
}