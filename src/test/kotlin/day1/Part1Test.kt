package day1

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class Part1Test {

    private lateinit var underTest: FuelCounterUpper

    @Before
    fun setUp() {
        underTest = FuelCounterUpper()
    }

    @Test
    fun massOf12Needs2Fuel() {
        val result = underTest.calculateFuelRequirement(12)

        assertEquals(2, result)
    }

    @Test
    fun massOf14Needs2Fuel() {
        val result = underTest.calculateFuelRequirement(14)

        assertEquals(2, result)
    }

    @Test
    fun massOf1969Need654Fuel() {
        val result = underTest.calculateFuelRequirement(1969)

        assertEquals(654, result)
    }

    @Test
    fun massOf100756Needs33583() {
        val result = underTest.calculateFuelRequirement(100756)

        assertEquals(33583, result)
    }
}