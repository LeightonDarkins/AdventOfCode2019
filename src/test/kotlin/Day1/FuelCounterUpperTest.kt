package Day1

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FuelCounterUpperTest {

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