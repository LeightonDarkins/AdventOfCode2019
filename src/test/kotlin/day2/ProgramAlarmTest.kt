package day2

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProgramAlarmTest {
    private lateinit var underTest: ProgramAlarm

    @Before
    fun setUp() {
        underTest = ProgramAlarm()
    }

    @Test
    fun example1() {
        val intCode = mutableListOf(1,0,0,0,99)

        val result = underTest.executeIntCodeProgram(intCode)

        assertEquals(2, result[0])
    }

    @Test
    fun example2() {
        val result = underTest.executeIntCodeProgram(mutableListOf(2,3,0,3,99))

        assertEquals(6, result[3])
    }

    @Test
    fun example3() {
        val result = underTest.executeIntCodeProgram(mutableListOf(2,4,4,5,99,0))

        assertEquals(9801, result[5])
    }

    @Test
    fun example4() {
        val result = underTest.executeIntCodeProgram(mutableListOf(1,1,1,4,99,5,6,0,99))

        assertEquals(2, result[4])
        assertEquals(30, result[0])
    }
}