package day1

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class Part2Test {

    private lateinit var underTest: FuelCounterUpper

    @Before
    fun setUp() {
        underTest = FuelCounterUpper()
    }

    @Test
    fun massOf14Requires2FuelRecursively() {
        val result = underTest.calculateFuelRequirementRecursively(14)

        assertEquals(2, result)
    }

    @Test
    fun massOf1969Requires966FuelRecursively() {
        val result = underTest.calculateFuelRequirementRecursively(1969)

        assertEquals(966, result)
    }

    @Test
    fun massOf100756Requires50346FuelRecursively() {
        val result = underTest.calculateFuelRequirementRecursively(100756)

        assertEquals(50346, result)
    }
}