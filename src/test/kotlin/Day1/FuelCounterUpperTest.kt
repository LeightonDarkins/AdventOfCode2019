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
        val result = underTest.doIt(12)

        assertEquals(2, result)
    }

    @Test
    fun massOf14Needs2Fuel() {
        val result = underTest.doIt(14)

        assertEquals(2, result)
    }

    @Test
    fun massOf1969Need654Fuel() {
        val result = underTest.doIt(1969)

        assertEquals(654, result)
    }

    @Test
    fun massOf100756Needs33583() {
        val result = underTest.doIt(100756)

        assertEquals(33583, result)
    }
}